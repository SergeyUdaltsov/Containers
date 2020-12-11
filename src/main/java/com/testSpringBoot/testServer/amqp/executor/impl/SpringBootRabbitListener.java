package com.testSpringBoot.testServer.amqp.executor.impl;

import com.testSpringBoot.testServer.utils.JsonUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author Serhii_Udaltsov on 12/11/2020
 */
public class SpringBootRabbitListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        byte[] body = message.getBody();
        String receivedMessage = JsonUtils.parseBytes(body, String.class);
        System.out.println("Message from rabbit ------ " + receivedMessage);
    }
}
