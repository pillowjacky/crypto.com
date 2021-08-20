package com.crypto.challenge.transaction;

import java.util.List;

public interface TransactionQueryService {
    
    public List<Transaction> getCustomerTransactions(Long CustomerId);
}
