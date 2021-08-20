package com.crypto.challenge.transaction;

import java.math.BigDecimal;

public class TransactionTransferCommand implements TransactionCommand {

    private String currency;

    private BigDecimal amount;

    private Long counterpartyId;

    private String counterpartyCurrency;

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

    public Long getCounterpartyId() {
        return this.counterpartyId;
    }

    public void setCounterpartyId(Long counterpartyId) {
        this.counterpartyId = counterpartyId;
    }

    public String getCounterpartyCurrency() {
        return this.counterpartyCurrency;
    }

    public void setCounterpartyCurrency(String counterpartyCurrency) {
        this.counterpartyCurrency = counterpartyCurrency;
    }    
}
