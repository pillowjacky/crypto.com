package com.crypto.challenge.transaction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_transaction")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_date", nullable = false)
    private OffsetDateTime transactionDate;

    @Column(name = "transaction_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "counterparty_id")
    private Long counterpartyId;

    @Column(name = "counterparty_currency")
    private String counterpartyCurrency;

    @Column(name = "counterparty_amount")
    private BigDecimal counterpartyAmount;

    @Column(name = "rate_at")
    private BigDecimal rateAt;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(OffsetDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

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

    public BigDecimal getRateAt() {
        return this.rateAt;
    }

    public void setRateAt(BigDecimal rateAt) {
        this.rateAt = rateAt;
    }
}
