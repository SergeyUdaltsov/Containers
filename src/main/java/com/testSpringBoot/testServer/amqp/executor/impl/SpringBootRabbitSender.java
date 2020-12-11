package com.testSpringBoot.testServer.amqp.executor.impl;

import com.testSpringBoot.testServer.amqp.executor.IRabbitSender;
import org.springframework.amqp.core.AmqpTemplate;

/**
 * @author Serhii_Udaltsov on 12/11/2020
 */
public class SpringBootRabbitSender implements IRabbitSender {

    private AmqpTemplate amqpTemplate;
    private String exchange;

    public SpringBootRabbitSender(AmqpTemplate amqpTemplate, String exchange) {
        this.amqpTemplate = amqpTemplate;
        this.exchange = exchange;
    }

    @Override
    public void execute(String message, String routingKey) {
        amqpTemplate.convertAndSend(exchange, routingKey, message);
    }

    @Override
    public void executeAsync(String message) {

    }
}
