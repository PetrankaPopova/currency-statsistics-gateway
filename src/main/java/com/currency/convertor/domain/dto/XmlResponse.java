package com.currency.convertor.domain.dto;

import com.currency.convertor.domain.entity.CurrencyData;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "response")
public class XmlResponse {
    List<CurrencyData> stats;
    Long period;

    @XmlElement
    public List<CurrencyData> getStats() {
        return stats;
    }

    public void setStats(List<CurrencyData> stats) {
        this.stats = stats;
    }

    @XmlElement
    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }

    private String requestId;
    private Long consumer;
    private String currency;
    private CurrencyData data;

    public XmlResponse() {
        // Default constructor for JAXB
    }

    public XmlResponse(
            String requestId,
            Long consumer,
            String currency,
            CurrencyData data,
            List<CurrencyData> stats,
            Long period
    ) {
        this.requestId = requestId;
        this.consumer = consumer;
        this.currency = currency;
        this.data = data;
        this.stats = stats;
        this.period = period;
    }

    @XmlElement
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @XmlElement
    public Long getConsumer() {
        return consumer;
    }

    public void setConsumer(Long consumer) {
        this.consumer = consumer;
    }

    @XmlElement
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @XmlElement
    public CurrencyData getData() {
        return data;
    }

    public void setData(CurrencyData data) {
        this.data = data;
    }
}
