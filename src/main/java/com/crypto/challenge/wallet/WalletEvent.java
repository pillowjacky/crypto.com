package com.crypto.challenge.wallet;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.crypto.challenge.transaction.WalletEventType;

@Entity
@Table(name = "customer_wallet_event")
public class WalletEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "event_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private WalletEventType eventType;

    @Column(name = "event_version", nullable = false)
    private Short eventVersion;

    @Column(name = "event_content", nullable = false)
    private String eventContent;

    @Column(name = "create_at", nullable = false)
    private OffsetDateTime createAt;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public WalletEventType getEventType() {
        return this.eventType;
    }

    public void setEventType(WalletEventType eventType) {
        this.eventType = eventType;
    }

    public Short getEventVersion() {
        return this.eventVersion;
    }

    public void setEventVersion(Short eventVersion) {
        this.eventVersion = eventVersion;
    }

    public String getEventContent() {
        return this.eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public OffsetDateTime getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(OffsetDateTime createAt) {
        this.createAt = createAt;
    }
}
