package com.crypto.challenge.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionQueryServiceImpl implements TransactionQueryService {
    
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getCustomerTransactions(Long customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }
}
