package com.currency.convertor.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class CurrencyApiResponse implements Serializable {
    private String currency;
    private boolean success;
    private Map<String, Double> rates;
    //private LocalDateTime date;

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

   /* @JsonDeserialize(using = LocalDateTimeDeserializer.class)
   // @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }*/
    public boolean isSuccess() {
        return this.success;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

}
