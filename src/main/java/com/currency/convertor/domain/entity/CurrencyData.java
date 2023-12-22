package com.currency.convertor.domain.entity;

import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "currency_data")
public class CurrencyData extends BaseEntity {
    private String currency;
    private Double rate;
    private Date date;
    private Long timestamp;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public CurrencyData() {
    }


    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Column(name = "rate")
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
}
