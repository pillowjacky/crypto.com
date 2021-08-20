package com.crypto.challenge.wallet;

import java.math.BigDecimal;

public class WalletExchangeCommand implements WalletCommand {

    private String currencyFrom;

    private BigDecimal amountFrom;

    private String currencyTo;

    private BigDecimal amountTo;

    public String getCurrencyFrom() {
        return this.currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public BigDecimal getAmountFrom() {
        return this.amountFrom;
    }

    public void setAmountFrom(BigDecimal amountFrom) {
        this.amountFrom = amountFrom;
    }

    public String getCurrencyTo() {
        return this.currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public BigDecimal getAmountTo() {
        return this.amountTo;
    }

    public void setAmountTo(BigDecimal amountTo) {
        this.amountTo = amountTo;
    }
}
