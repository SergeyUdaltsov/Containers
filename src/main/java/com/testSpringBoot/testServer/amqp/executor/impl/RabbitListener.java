package com.testSpringBoot.testServer.amqp.executor.impl;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author Serhii_Udaltsov on 12/10/2020
 */
@Component
public class RabbitListener implements MessageListener {

    @Override
    public void onMessage(Message message) {

    }

    //    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = "serg-request-queue")
//    public void listen(String in) {
//        System.out.println(in);
//    }
}
