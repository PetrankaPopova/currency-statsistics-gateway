package com.currency.convertor.client;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQSender {

    private final AmqpTemplate rabbitTemplate;

    public RabbitMQSender(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessageToRequestHistory(String message) {
        rabbitTemplate.convertAndSend("request_history", message);
    }

    public void sendMessageToCurrencyStats(String message) {
        rabbitTemplate.convertAndSend("currency_stats", message);
    }
}
