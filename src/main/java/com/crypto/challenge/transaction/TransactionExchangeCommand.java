package com.crypto.challenge.transaction;

import java.math.BigDecimal;

public class TransactionExchangeCommand implements TransactionCommand {

    private String currencyFrom;

    private BigDecimal amount;

    private String currencyTo;

    public String getCurrencyFrom() {
        return this.currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrencyTo() {
        return this.currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }   
}
