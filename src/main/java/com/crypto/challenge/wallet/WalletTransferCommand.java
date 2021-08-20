package com.crypto.challenge.wallet;

import java.math.BigDecimal;

public class WalletTransferCommand implements WalletCommand {

    private String currency;

    private BigDecimal amount;

    private Long counterpartyId;

    private String counterpartyCurrency;

    private BigDecimal counterpartyAmount;

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

    public BigDecimal getCounterpartyAmount() {
        return this.counterpartyAmount;
    }

    public void setCounterpartyAmount(BigDecimal counterpartyAmount) {
        this.counterpartyAmount = counterpartyAmount;
    }
}
