package com.testSpringBoot.testServer.amqp.processor;

import com.testSpringBoot.testServer.amqp.converter.impl.MessageType;
import org.springframework.amqp.core.Message;

/**
 * @author Serhii_Udaltsov on 12/12/2020
 */
public interface IMessageProcessor {

    void processMessage(Message message);

    MessageType getSupportedMessageType();
}
