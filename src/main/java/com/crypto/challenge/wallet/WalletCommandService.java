package com.crypto.challenge.wallet;

public interface WalletCommandService {

    public void deposit(Long customerId, WalletDepositCommand command) throws Exception;

    public void exchange(Long customerId, WalletExchangeCommand command) throws Exception;

    public void transfer(Long customerId, WalletTransferCommand command) throws Exception;

    public void withdraw(Long customerId, WalletWithdrawCommand command) throws Exception;
}
