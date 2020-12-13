package com.testSpringBoot.testServer.amqp.processor;

import com.testSpringBoot.testServer.amqp.converter.impl.MessageType;

/**
 * @author Serhii_Udaltsov on 12/12/2020
 */
public interface IProcessorFactory {

    IMessageProcessor getMessageProcessor(MessageType type);
}
