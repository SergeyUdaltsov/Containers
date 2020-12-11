package com.testSpringBoot.testServer.amqp.executor.impl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.testSpringBoot.testServer.amqp.executor.IRabbitSender;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * @author Serhii_Udaltsov on 12/10/2020
 */
public class RabbitSender implements IRabbitSender {

    private String exchangeName;
    private String requestQueueName;
    private String responseQueueName;
    private ConnectionFactory connectionFactory;

    @Autowired
    public RabbitSender(ConnectionFactory connectionFactory, String exchangeName, String requestQueueName,
                        String responseQueueName) {
        this.requestQueueName = requestQueueName;
        this.responseQueueName = responseQueueName;
        this.exchangeName = exchangeName;
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void execute(String message, String routingKey) {
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            String correlationId = UUID.randomUUID().toString();
            AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                    .correlationId(correlationId)
                    .replyTo(responseQueueName)
                    .build();
            channel.basicPublish(exchangeName, routingKey, properties, message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executeAsync(String message) {

    }
}
