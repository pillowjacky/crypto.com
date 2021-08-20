package com.crypto.challenge.wallet;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import com.crypto.challenge.exception.InsufficientFundsException;
import com.crypto.challenge.transaction.WalletEventType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletCommandServiceImpl implements WalletCommandService {

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
    }

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletEventRepository walletEventRepository;

    @Override
    @Transactional
    public void deposit(Long customerId, WalletDepositCommand command) throws Exception {
        Wallet wallet = walletRepository.findByCustomerIdAndCurrency(customerId, command.getCurrency());
        wallet.setAmount(wallet.getAmount().add(command.getAmount()));
        walletRepository.save(wallet);

        saveEvent(customerId, command);
    }

    @Override
    @Transactional
    public void exchange(Long customerId, WalletExchangeCommand command) throws Exception {
        Wallet walletFrom = walletRepository.findByCustomerIdAndCurrency(customerId, command.getCurrencyFrom());
        if (walletFrom.getAmount().compareTo(command.getAmountFrom()) < 0) {
            throw new InsufficientFundsException("Insufficient Funds");
        }
        walletFrom.setAmount(walletFrom.getAmount().subtract(command.getAmountFrom()));
        walletRepository.save(walletFrom);

        Wallet walletTo = walletRepository.findByCustomerIdAndCurrency(customerId, command.getCurrencyTo());
        walletTo.setAmount(walletTo.getAmount().add(command.getAmountTo()));
        walletRepository.save(walletTo);

        saveEvent(customerId, command);
    }

    @Override
    @Transactional
    public void transfer(Long customerId, WalletTransferCommand command) throws Exception {
        Wallet walletFrom = walletRepository.findByCustomerIdAndCurrency(customerId, command.getCurrency());
        if (walletFrom.getAmount().compareTo(command.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient Funds");
        }
        walletFrom.setAmount(walletFrom.getAmount().subtract(command.getAmount()));
        walletRepository.save(walletFrom);

        Wallet walletTo = walletRepository.findByCustomerIdAndCurrency(command.getCounterpartyId(),
                command.getCounterpartyCurrency());
        walletTo.setAmount(walletTo.getAmount().add(command.getCounterpartyAmount()));
        walletRepository.save(walletTo);

        saveEvent(customerId, command);
    }

    @Override
    @Transactional
    public void withdraw(Long customerId, WalletWithdrawCommand command) throws Exception {
        Wallet walletFrom = walletRepository.findByCustomerIdAndCurrency(customerId, command.getCurrency());
        if (walletFrom.getAmount().compareTo(command.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient Funds");
        }
        walletFrom.setAmount(walletFrom.getAmount().subtract(command.getAmount()));
        walletRepository.save(walletFrom);

        saveEvent(customerId, command);
    }

    private void saveEvent(Long customerId, WalletCommand command) throws JsonProcessingException {
        if (command instanceof WalletDepositCommand) {
            saveEvent(customerId, WalletEventType.DEPOSIT, command);
            return;
        }
        if (command instanceof WalletExchangeCommand) {
            saveEvent(customerId, WalletEventType.EXCHANGE, command);
            return;
        }
        if (command instanceof WalletTransferCommand) {
            saveEvent(customerId, WalletEventType.TRANSFER_IN, command);
            saveEvent(customerId, WalletEventType.TRANSFER_OUT, command);
            return;
        }
        if (command instanceof WalletWithdrawCommand) {
            saveEvent(customerId, WalletEventType.WITHDRAW, command);
            return;
        }
    }

    private void saveEvent(Long customerId, WalletEventType eventType, WalletCommand command)
            throws JsonProcessingException {
        WalletEvent event = new WalletEvent();
        event.setCustomerId(customerId);
        event.setEventType(eventType);
        event.setEventVersion(Short.valueOf("1"));
        event.setEventContent(OBJECT_MAPPER.writeValueAsString(command));
        event.setCreateAt(OffsetDateTime.now(ZoneOffset.UTC));
        walletEventRepository.save(event);
    }
}
