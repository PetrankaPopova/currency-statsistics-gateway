package com.currency.convertor.domain.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CurrencyApiRequest {
    private String currency;
    private Map<String, Double> rates;
    private LocalDateTime date;


    public CurrencyApiRequest() {
        this.rates = new HashMap<>();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isSuccess() {
        return true;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
