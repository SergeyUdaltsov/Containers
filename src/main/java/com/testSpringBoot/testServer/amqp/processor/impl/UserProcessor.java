package com.testSpringBoot.testServer.amqp.processor.impl;

import com.testSpringBoot.testServer.amqp.converter.IRabbitConverter;
import com.testSpringBoot.testServer.amqp.converter.impl.MessageType;
import com.testSpringBoot.testServer.amqp.processor.IMessageProcessor;
import com.testSpringBoot.testServer.model.User;
import com.testSpringBoot.testServer.utils.JsonUtils;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Serhii_Udaltsov on 12/12/2020
 */
@Service
public class UserProcessor implements IMessageProcessor {

    private IRabbitConverter converter;

    @Autowired
    public UserProcessor(IRabbitConverter converter) {
        this.converter = converter;
    }

    @Override
    public void processMessage(Message message) {
        String queueName = message.getMessageProperties().getConsumerQueue();
        User user = converter.unconvert(message, User.class);
        System.out.println("User got from rabbit, queue: " + queueName + ", \npayload: " + JsonUtils.convertObjectToJson(user));
    }

    @Override
    public MessageType getSupportedMessageType() {
        return MessageType.USER;
    }
}
