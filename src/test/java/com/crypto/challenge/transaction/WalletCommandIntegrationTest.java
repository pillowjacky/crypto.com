package com.crypto.challenge.transaction;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import com.crypto.challenge.exception.InsufficientFundsException;
import com.crypto.challenge.wallet.WalletCommandService;
import com.crypto.challenge.wallet.WalletDepositCommand;
import com.crypto.challenge.wallet.WalletExchangeCommand;
import com.crypto.challenge.wallet.WalletTransferCommand;
import com.crypto.challenge.wallet.WalletWithdrawCommand;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WalletCommandIntegrationTest {

    @Autowired
    private WalletCommandService walletCommandService;

    @Test
    public void testDeposit_succeed() throws Exception {
        assertDoesNotThrow(() -> {
            WalletDepositCommand command = new WalletDepositCommand();
            command.setCurrency("HKD");
            command.setAmount(new BigDecimal("1"));
            walletCommandService.deposit(Long.valueOf("1"), command);
        });
    }

    @Test
    public void testExchange_succeed() throws Exception {
        assertDoesNotThrow(() -> {
            WalletExchangeCommand command = new WalletExchangeCommand();
            command.setCurrencyFrom("HKD");
            command.setAmountFrom(new BigDecimal("1"));
            command.setCurrencyTo("USD");
            command.setAmountTo(new BigDecimal("0.12"));
            walletCommandService.exchange(Long.valueOf("1"), command);
        });
    }

    @Test
    public void testExchange_failed_insufficientFunds() throws Exception {
        assertThrows(InsufficientFundsException.class, () -> {
            WalletExchangeCommand command = new WalletExchangeCommand();
            command.setCurrencyFrom("HKD");
            command.setAmountFrom(new BigDecimal("1000"));
            command.setCurrencyTo("USD");
            command.setAmountTo(new BigDecimal("120"));
            walletCommandService.exchange(Long.valueOf("1"), command);
        });
    }

    @Test
    public void testTransfer_succeed() throws Exception {
        assertDoesNotThrow(() -> {
            WalletTransferCommand command = new WalletTransferCommand();
            command.setCurrency("HKD");
            command.setAmount(new BigDecimal("1"));
            command.setCounterpartyId(Long.valueOf("2"));
            command.setCounterpartyCurrency("HKD");
            command.setCounterpartyAmount(new BigDecimal("1"));
            walletCommandService.transfer(Long.valueOf("1"), command);
        });
    }

    @Test
    public void testTransfer_succeed_differentCurrency() throws Exception {
        assertDoesNotThrow(() -> {
            WalletTransferCommand command = new WalletTransferCommand();
            command.setCurrency("HKD");
            command.setAmount(new BigDecimal("1"));
            command.setCounterpartyId(Long.valueOf("2"));
            command.setCounterpartyCurrency("USD");
            command.setCounterpartyAmount(new BigDecimal("0.12"));
            walletCommandService.transfer(Long.valueOf("1"), command);
        });
    }

    @Test
    public void testTransfer_failed_insufficientFunds() throws Exception {
        assertThrows(InsufficientFundsException.class, () -> {
            WalletTransferCommand command = new WalletTransferCommand();
            command.setCurrency("HKD");
            command.setAmount(new BigDecimal("1000"));
            command.setCounterpartyId(Long.valueOf("2"));
            command.setCounterpartyCurrency("HKD");
            command.setCounterpartyAmount(new BigDecimal("120"));
            walletCommandService.transfer(Long.valueOf("1"), command);
        });
    }

    @Test
    public void testWithdraw_succeed() throws Exception {
        assertDoesNotThrow(() -> {
            WalletWithdrawCommand command = new WalletWithdrawCommand();
            command.setCurrency("HKD");
            command.setAmount(new BigDecimal("1"));
            walletCommandService.withdraw(Long.valueOf("1"), command);
        });
    }

    @Test
    public void testWithdraw_failed_insufficientFunds() throws Exception {
        assertThrows(InsufficientFundsException.class, () -> {
            WalletWithdrawCommand command = new WalletWithdrawCommand();
            command.setCurrency("HKD");
            command.setAmount(new BigDecimal("1000"));
            walletCommandService.withdraw(Long.valueOf("1"), command);
        });
    }
}
