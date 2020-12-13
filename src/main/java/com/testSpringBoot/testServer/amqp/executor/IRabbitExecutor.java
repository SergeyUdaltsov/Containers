package com.testSpringBoot.testServer.amqp.executor;

import org.springframework.amqp.core.Message;

/**
 * @author Serhii_Udaltsov on 12/10/2020
 */
public interface IRabbitExecutor {

    void send(Message message, String routingKey);

    void listen(Message message);

}
