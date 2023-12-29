package com.currency.convertor.domain.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * The XmlRequest class is a DTO (Data Transfer Object) representing an XML request with the root element "command".
 * It is used for processing XML requests in the application.
 *
 * Properties:
 * - id: A unique identifier for the XML request.
 * - get: An inner class representing the "get" element in the XML request.
 * - history: An inner class representing the "history" element in the XML request.
 *
 * Inner Class GetRequest:
 *   - consumer: The value of the "consumer" attribute in the "get" element.
 *   - currency: The value of the "currency" element in the "get" element.
 *
 * Inner Class HistoryRequest:
 *   - consumer: The value of the "consumer" attribute in the "history" element.
 *   - period: The value of the "period" attribute in the "history" element.
 *   - currency: The value of the "currency" attribute in the "history" element.
 *
 * This class is annotated with JAXB annotations to specify XML elements and attributes.
 * The XmlRootElement annotation is used to define the root element name.
 */

@XmlRootElement(name = "command")
public class XmlRequest {

    private String id;
    private GetRequest get;
    private HistoryRequest history;

    public XmlRequest() {
    }

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
