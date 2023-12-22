package com.currency.convertor.domain.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CurrencyApiResponse implements Serializable {
    private String currency;
    private boolean success;
    private Map<String, Double> rates;
    private Date date;
    private Long timestamp;

    public CurrencyApiResponse() {
        this.rates = new HashMap<>();
    }

    public CurrencyApiResponse(String currency, Double aDouble) {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
