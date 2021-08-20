package com.crypto.challenge.transaction;

import java.math.BigDecimal;

public class TransactionWithdrawCommand implements TransactionCommand {

    private String currency;

    private BigDecimal amount;

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
