package com.currency.convertor.domain.dto;

import com.currency.convertor.domain.entity.CurrencyData;

import java.util.List;


public class CurrencyStatsHistoryResponse {

    private String requestId;
    private String consumer;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<CurrencyData> getStats() {
        return stats;
    }

    public void setStats(List<CurrencyData> stats) {
        this.stats = stats;
    }

    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }

    public CurrencyStatsHistoryResponse(String requestId, String consumer, String currency, List<CurrencyData> stats, Long period) {
        this.requestId = requestId;
        this.consumer = consumer;
        this.currency = currency;
        this.stats = stats;
        this.period = period;
    }

    private String currency;
    List<CurrencyData> stats;
    Long period;
}
