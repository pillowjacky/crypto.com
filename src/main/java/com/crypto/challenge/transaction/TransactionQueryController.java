package com.crypto.challenge.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionQueryController {

    @Autowired
    private TransactionQueryService transactionQueryService;

    @GetMapping("/customer/{id}/transactions")
    public List<Transaction> getCustomerTransactions(@PathVariable Long id) {
        return transactionQueryService.getCustomerTransactions(id);
    }
}
