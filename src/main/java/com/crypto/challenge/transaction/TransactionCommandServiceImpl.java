package com.crypto.challenge.transaction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import com.crypto.challenge.exception.ExchangeToSameCurrencyException;
import com.crypto.challenge.wallet.WalletCommandService;
import com.crypto.challenge.wallet.WalletDepositCommand;
import com.crypto.challenge.wallet.WalletExchangeCommand;
import com.crypto.challenge.wallet.WalletTransferCommand;
import com.crypto.challenge.wallet.WalletWithdrawCommand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionCommandServiceImpl implements TransactionCommandService {

    private static final Map<String, BigDecimal> DUMMY_RATE;

    private static final ObjectMapper OBJECT_MAPPER;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionEventRepository transactionEventRepository;

    @Autowired
    private WalletCommandService walletCommandService;

    static {
        DUMMY_RATE = new HashMap<>();
        DUMMY_RATE.put("HKDUSD", new BigDecimal("0.128975"));
        DUMMY_RATE.put("USDHKD", new BigDecimal("7.753410"));

        OBJECT_MAPPER = new ObjectMapper();
    }

    @Override
    @Transactional
    public void deposit(Long customerId, TransactionDepositCommand command) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(OffsetDateTime.now(ZoneOffset.UTC));
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setCustomerId(customerId);
        transaction.setCurrency(command.getCurrency());
        transaction.setAmount(command.getAmount());
        transactionRepository.save(transaction);

        WalletDepositCommand walletCommand = new WalletDepositCommand();
        walletCommand.setCurrency(command.getCurrency());
        walletCommand.setAmount(command.getAmount());
        walletCommandService.deposit(customerId, walletCommand);

        saveEvent(customerId, command);
    }

    @Override
    @Transactional
    public void exchange(Long customerId, TransactionExchangeCommand command) throws Exception {
        String currency = command.getCurrencyFrom();
        BigDecimal amount = command.getAmount();
        String counterpartyCurrency = command.getCurrencyTo();
        BigDecimal rate;
        System.out.println(currency);
        if (currency.equals(counterpartyCurrency)) {
            throw new ExchangeToSameCurrencyException("Exchange to same currency");
        } else {
            rate = DUMMY_RATE.get(currency + counterpartyCurrency);
        }
        BigDecimal counterpartyAmount = amount.multiply(rate);

        Transaction transaction = new Transaction();
        transaction.setTransactionDate(OffsetDateTime.now(ZoneOffset.UTC));
        transaction.setTransactionType(TransactionType.EXCHANGE);
        transaction.setCustomerId(customerId);
        transaction.setCurrency(currency);
        transaction.setAmount(amount);
        transaction.setCounterpartyId(customerId);
        transaction.setCounterpartyCurrency(counterpartyCurrency);
        transaction.setCounterpartyAmount(counterpartyAmount);
        transaction.setRateAt(rate);
        transactionRepository.save(transaction);

        WalletExchangeCommand walletCommand = new WalletExchangeCommand();
        walletCommand.setCurrencyFrom(currency);
        walletCommand.setAmountFrom(amount);
        walletCommand.setCurrencyTo(counterpartyCurrency);
        walletCommand.setAmountTo(counterpartyAmount);
        walletCommandService.exchange(customerId, walletCommand);

        saveEvent(customerId, command);
    }

    @Override
    @Transactional
    public void transfer(Long customerId, TransactionTransferCommand command) throws Exception {
        String currency = command.getCurrency();
        BigDecimal amount = command.getAmount();
        String counterpartyCurrency = command.getCounterpartyCurrency();
        BigDecimal rate;
        if (currency.equals(counterpartyCurrency)) {
            rate = BigDecimal.ONE;
        } else {
            rate = DUMMY_RATE.get(currency + counterpartyCurrency);
        }
        BigDecimal counterpartyAmount = amount.multiply(rate);

        Transaction customerTransaction = new Transaction();
        customerTransaction.setTransactionDate(OffsetDateTime.now(ZoneOffset.UTC));
        customerTransaction.setTransactionType(TransactionType.TRANSFER_OUT);
        customerTransaction.setCustomerId(customerId);
        customerTransaction.setCurrency(currency);
        customerTransaction.setAmount(amount);
        customerTransaction.setCounterpartyId(command.getCounterpartyId());
        customerTransaction.setCounterpartyCurrency(counterpartyCurrency);
        customerTransaction.setCounterpartyAmount(counterpartyAmount);
        customerTransaction.setRateAt(rate);
        transactionRepository.save(customerTransaction);

        Transaction counterpartyTransaction = new Transaction();
        counterpartyTransaction.setTransactionDate(OffsetDateTime.now(ZoneOffset.UTC));
        counterpartyTransaction.setTransactionType(TransactionType.TRANSFER_IN);
        counterpartyTransaction.setCustomerId(command.getCounterpartyId());
        counterpartyTransaction.setCurrency(counterpartyCurrency);
        counterpartyTransaction.setAmount(counterpartyAmount);
        counterpartyTransaction.setCounterpartyId(customerId);
        counterpartyTransaction.setCounterpartyCurrency(currency);
        counterpartyTransaction.setCounterpartyAmount(amount);
        counterpartyTransaction.setRateAt(rate);
        transactionRepository.save(counterpartyTransaction);

        WalletTransferCommand walletCommand = new WalletTransferCommand();
        walletCommand.setCurrency(currency);
        walletCommand.setAmount(amount);
        walletCommand.setCounterpartyId(command.getCounterpartyId());
        walletCommand.setCounterpartyCurrency(counterpartyCurrency);
        walletCommand.setCounterpartyAmount(counterpartyAmount);
        walletCommandService.transfer(customerId, walletCommand);

        saveEvent(customerId, command);
    }

    @Override
    @Transactional
    public void withdraw(Long customerId, TransactionWithdrawCommand command) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(OffsetDateTime.now(ZoneOffset.UTC));
        transaction.setTransactionType(TransactionType.WITHDRAW);
        transaction.setCustomerId(customerId);
        transaction.setCurrency(command.getCurrency());
        transaction.setAmount(command.getAmount());
        transactionRepository.save(transaction);

        WalletWithdrawCommand walletCommand = new WalletWithdrawCommand();
        walletCommand.setCurrency(command.getCurrency());
        walletCommand.setAmount(command.getAmount());
        walletCommandService.withdraw(customerId, walletCommand);

        saveEvent(customerId, command);
    }

    private void saveEvent(Long customerId, TransactionCommand command) throws JsonProcessingException {
        if (command instanceof TransactionDepositCommand) {
            saveEvent(customerId, TransactionEventType.DEPOSIT, command);
            return;
        }
        if (command instanceof TransactionExchangeCommand) {
            saveEvent(customerId, TransactionEventType.EXCHANGE, command);
            return;
        }
        if (command instanceof TransactionTransferCommand) {
            saveEvent(customerId, TransactionEventType.TRANSFER_IN, command);
            saveEvent(customerId, TransactionEventType.TRANSFER_OUT, command);
            return;
        }
        if (command instanceof TransactionWithdrawCommand) {
            saveEvent(customerId, TransactionEventType.WITHDRAW, command);
            return;
        }
    }

    private void saveEvent(Long customerId, TransactionEventType eventType, TransactionCommand command)
            throws JsonProcessingException {
        TransactionEvent event = new TransactionEvent();
        event.setCustomerId(customerId);
        event.setEventType(eventType);
        event.setEventVersion(Short.valueOf("1"));
        event.setEventContent(OBJECT_MAPPER.writeValueAsString(command));
        event.setCreateAt(OffsetDateTime.now(ZoneOffset.UTC));
        transactionEventRepository.save(event);
    }
}
