package com.crypto.challenge.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionCommandController {

    @Autowired
    private TransactionCommandService transactionCommandService;

    @PostMapping("/customer/{id}/deposit")
    public ResponseEntity<Void> deposit(@PathVariable Long id, @RequestBody TransactionDepositCommand command)
            throws Exception {
        transactionCommandService.deposit(id, command);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/customer/{id}/exchange")
    public ResponseEntity<Void> exchange(@PathVariable Long id, @RequestBody TransactionExchangeCommand command)
            throws Exception {
        transactionCommandService.exchange(id, command);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/customer/{id}/transfer")
    public ResponseEntity<Void> transfer(@PathVariable Long id, @RequestBody TransactionTransferCommand command)
            throws Exception {
        transactionCommandService.transfer(id, command);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/customer/{id}/withdraw")
    public ResponseEntity<Void> withdraw(@PathVariable Long id, @RequestBody TransactionWithdrawCommand command)
            throws Exception {
        transactionCommandService.withdraw(id, command);
        return ResponseEntity.ok().build();
    }
}
