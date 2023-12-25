package com.currency.convertor.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(schema = "request_details")
public class RequestDetails extends BaseEntity{
    private String requestId;
    private Long client;
    private String currency;

    private LocalDateTime timestamp;

    public RequestDetails() {
    }

    @Column(name = "request_id", nullable = false)
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Column(name = "client", nullable = false)
    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    @Column(name = "currency", nullable = false)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Column(name = "timestamp", nullable = false)
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}


