package com.currency.convertor.domain.entity;

import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;
/**
 * The CurrencyData class represents an entity storing currency-related data in the database.
 * It extends the BaseEntity class, inheriting the common identifier property (id).
 *
 * Properties:
 * - currency: The currency code (e.g., USD).
 * - rate: The exchange rate for the currency.
 * - date: The date associated with the currency data.
 * - timestamp: A timestamp indicating when the currency data was recorded.
 *
 * JPA Annotations:
 * - @Entity: Indicates that this class is an entity and is mapped to a database table.
 * - @Table: Specifies the name of the database table to which this entity is mapped.
 * - @Column: Specifies the mapping of a persistent property or field.
 * - @NotNull: Indicates that the associated property must not be null.
 * - @DateTimeFormat: Specifies the format for formatting and parsing a Date or Calendar property.
 */

@Entity
@Table(name = "currency_data")
public class CurrencyData extends BaseEntity {
    private String currency;
    private Double rate;
    private Date date;
    private Long timestamp;


    public CurrencyData() {
    }

    @Column(name = "currency", nullable = false)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Column(name = "rate", nullable = false)
    public Double getRate() {
        return rate;
    }

    public void setRate(Double exchangeRate) {
        this.rate = exchangeRate;
    }

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getDate() {
        return date;
    }

    public void setDate(Date lastUpdated) {
        this.date = lastUpdated;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
