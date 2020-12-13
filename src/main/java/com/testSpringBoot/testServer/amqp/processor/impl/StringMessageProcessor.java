package com.testSpringBoot.testServer.amqp.processor.impl;

import com.testSpringBoot.testServer.amqp.converter.IRabbitConverter;
import com.testSpringBoot.testServer.amqp.converter.impl.MessageType;
import com.testSpringBoot.testServer.amqp.processor.IMessageProcessor;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Serhii_Udaltsov on 12/12/2020
 */
@Service
public class StringMessageProcessor implements IMessageProcessor {

    private IRabbitConverter converter;

    @Autowired
    public StringMessageProcessor(IRabbitConverter converter) {
        this.converter = converter;
    }

    @Override
    public void processMessage(Message message) {
        String messageText = converter.unconvert(message, String.class);
        System.out.println("Received string message: " + messageText);
    }

    @Override
    public MessageType getSupportedMessageType() {
        return MessageType.STRING;
    }
}
