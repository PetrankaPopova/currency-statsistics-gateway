package com.currency.convertor.client;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQSender {
    private static final String CURRENCY_QUEUE = "currency_stats";
    private static final String HISTORY_QUEUE = "request_history";
    private final AmqpTemplate rabbitTemplate;

    public RabbitMQSender(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessageToRequestHistory(String message) {
        rabbitTemplate.convertAndSend(HISTORY_QUEUE, HISTORY_QUEUE, message);
    }

    public void sendMessageToCurrencyStats(String message) {
        rabbitTemplate.convertAndSend(CURRENCY_QUEUE, CURRENCY_QUEUE, message);
    }

    //@RabbitListener(bindings = @QueueBinding(value = @Queue(value = CURRENCY_QUEUE), exchange = @Exchange(value = CURRENCY_QUEUE), key = CURRENCY_QUEUE))
    public void listenCurrency(Message message){

    }

    //@RabbitListener(bindings = @QueueBinding(value = @Queue(value = HISTORY_QUEUE), exchange = @Exchange(value = HISTORY_QUEUE), key = HISTORY_QUEUE))
    public void listenHistory(Message message){

    }

}
