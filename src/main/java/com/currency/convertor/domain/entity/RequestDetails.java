package com.currency.convertor.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;
/**
 * The RequestDetails class represents an entity storing details of currency-related requests in the database.
 * It extends the BaseEntity class, inheriting the common identifier property (id).
 *
 * Properties:
 * - requestId: The unique identifier for the request.
 * - client: The client associated with the request.
 * - currency: The currency code associated with the request.
 * - timestamp: The timestamp indicating when the request details were recorded.
 *
 * JPA Annotations:
 * - @Entity: Indicates that this class is an entity and is mapped to a database table.
 * - @Table: Specifies the name of the database table to which this entity is mapped.
 * - @Column: Specifies the mapping of a persistent property or field.
 */
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


