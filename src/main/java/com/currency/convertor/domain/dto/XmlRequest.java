package com.currency.convertor.domain.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "command")
public class XmlRequest {

    private String id;
    private GetRequest get;
    private HistoryRequest history;

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "get")
    public GetRequest getGet() {
        return get;
    }

    public void setGet(GetRequest get) {
        this.get = get;
    }

    @XmlElement(name = "history")
    public HistoryRequest getHistory() {
        return history;
    }

    public void setHistory(HistoryRequest history) {
        this.history = history;
    }

    public static class GetRequest {
        private Long consumer;
        private String currency;

        @XmlAttribute
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
    }

    public static class HistoryRequest {
        private Long consumer;
        private String currency;
        private Long period;

        @XmlAttribute
        public Long getConsumer() {
            return consumer;
        }

        public void setConsumer(Long consumer) {
            this.consumer = consumer;
        }

        @XmlAttribute
        public Long getPeriod() {
            return period;
        }

        public void setPeriod(Long period) {
            this.period = period;
        }

        @XmlAttribute
        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }
    }
}
