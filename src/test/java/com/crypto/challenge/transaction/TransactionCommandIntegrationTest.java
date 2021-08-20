package com.crypto.challenge.transaction;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import com.crypto.challenge.exception.ExchangeToSameCurrencyException;
import com.crypto.challenge.exception.InsufficientFundsException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransactionCommandIntegrationTest {

    @Autowired
    private TransactionCommandService transactionCommandService;

    @Test
    public void testDeposit_succeed() throws Exception {
        assertDoesNotThrow(() -> {
            TransactionDepositCommand command = new TransactionDepositCommand();
            command.setCurrency("HKD");
            command.setAmount(new BigDecimal("1"));
            transactionCommandService.deposit(Long.valueOf("1"), command);
        });
    }

    @Test
    public void testExchange_succeed() throws Exception {
        assertDoesNotThrow(() -> {
            TransactionExchangeCommand command = new TransactionExchangeCommand();
            command.setCurrencyFrom("HKD");
            command.setAmount(new BigDecimal("1"));
            command.setCurrencyTo("USD");
            transactionCommandService.exchange(Long.valueOf("1"), command);
        });
    }

    @Test
    public void testExchange_failed_sameCurrency() throws Exception {
        assertThrows(ExchangeToSameCurrencyException.class, () -> {
            TransactionExchangeCommand command = new TransactionExchangeCommand();
            command.setCurrencyFrom("HKD");
            command.setAmount(new BigDecimal("1"));
            command.setCurrencyTo("HKD");
            transactionCommandService.exchange(Long.valueOf("1"), command);
        });
    }

    @Test
    public void testExchange_failed_insufficientFunds() throws Exception {
        assertThrows(InsufficientFundsException.class, () -> {
            TransactionExchangeCommand command = new TransactionExchangeCommand();
            command.setCurrencyFrom("HKD");
            command.setAmount(new BigDecimal("1000"));
            command.setCurrencyTo("USD");
            transactionCommandService.exchange(Long.valueOf("1"), command);
        });
    }

    @Test
    public void testTransfer_succeed() throws Exception {
        assertDoesNotThrow(() -> {
            TransactionTransferCommand command = new TransactionTransferCommand();
            command.setCurrency("HKD");
            command.setAmount(new BigDecimal("1"));
            command.setCounterpartyId(Long.valueOf("2"));
            command.setCounterpartyCurrency("HKD");
            transactionCommandService.transfer(Long.valueOf("1"), command);
        });
    }

    @Test
    public void testTransfer_succeed_differentCurrency() throws Exception {
        assertDoesNotThrow(() -> {
            TransactionTransferCommand command = new TransactionTransferCommand();
            command.setCurrency("HKD");
            command.setAmount(new BigDecimal("1"));
            command.setCounterpartyId(Long.valueOf("2"));
            command.setCounterpartyCurrency("USD");
            transactionCommandService.transfer(Long.valueOf("1"), command);
        });
    }

    @Test
    public void testTransfer_failed_insufficientFunds() throws Exception {
        assertThrows(InsufficientFundsException.class, () -> {
            TransactionTransferCommand command = new TransactionTransferCommand();
            command.setCurrency("HKD");
            command.setAmount(new BigDecimal("1000"));
            command.setCounterpartyId(Long.valueOf("2"));
            command.setCounterpartyCurrency("HKD");
            transactionCommandService.transfer(Long.valueOf("1"), command);
        });
    }

    @Test
    public void testWithdraw_succeed() throws Exception {
        assertDoesNotThrow(() -> {
            TransactionWithdrawCommand command = new TransactionWithdrawCommand();
            command.setCurrency("HKD");
            command.setAmount(new BigDecimal("1"));
            transactionCommandService.withdraw(Long.valueOf("1"), command);
        });
    }

    @Test
    public void testWithdraw_failed_insufficientFunds() throws Exception {
        assertThrows(InsufficientFundsException.class, () -> {
            TransactionWithdrawCommand command = new TransactionWithdrawCommand();
            command.setCurrency("HKD");
            command.setAmount(new BigDecimal("1000"));
            transactionCommandService.withdraw(Long.valueOf("1"), command);
        });
    }
}
