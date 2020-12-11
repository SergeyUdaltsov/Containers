package com.testSpringBoot.testServer.amqp.executor;

/**
 * @author Serhii_Udaltsov on 12/10/2020
 */
public interface IRabbitSender {

    void execute(String message, String routingKey);

    void executeAsync(String message);
}
