package com.currency.convertor.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "currency_data")
public class CurrencyData extends BaseEntity {
    private String currency;
    private Double rate;
    //private LocalDateTime date;

    public CurrencyData() {
    }


    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Column(name = "exchange_rate")
    public Double getRate() {
        return rate;
    }

    public void setRate(Double exchangeRate) {
        this.rate = exchangeRate;
    }

    /*@NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime lastUpdated) {
        this.date = lastUpdated;
    }*/
}
