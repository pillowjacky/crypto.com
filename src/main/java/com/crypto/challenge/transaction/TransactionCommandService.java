package com.crypto.challenge.transaction;

public interface TransactionCommandService {

    public void deposit(Long customerId, TransactionDepositCommand command) throws Exception;

    public void exchange(Long customerId, TransactionExchangeCommand command) throws Exception;

    public void transfer(Long customerId, TransactionTransferCommand command) throws Exception;

    public void withdraw(Long customerId, TransactionWithdrawCommand command) throws Exception;
}
