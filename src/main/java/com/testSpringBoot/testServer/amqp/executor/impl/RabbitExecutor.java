package com.testSpringBoot.testServer.amqp.executor.impl;

import com.testSpringBoot.testServer.amqp.converter.impl.MessageType;
import com.testSpringBoot.testServer.amqp.executor.IRabbitExecutor;
import com.testSpringBoot.testServer.amqp.processor.IMessageProcessor;
import com.testSpringBoot.testServer.amqp.processor.IProcessorFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author Serhii_Udaltsov on 12/11/2020
 */
public class RabbitExecutor implements IRabbitExecutor {

    private AmqpTemplate amqpTemplate;
    private IProcessorFactory processorFactory;

    public RabbitExecutor(AmqpTemplate amqpTemplate, IProcessorFactory processorFactory) {
        this.amqpTemplate = amqpTemplate;
        this.processorFactory = processorFactory;
    }

    @Override
    public void send(Message message, String routingKey) {
        amqpTemplate.convertAndSend(routingKey, message);
    }

    @Override
    @RabbitListener(queues = {"serg-request-queue", "serg-request-second-queue"},
            containerFactory = "listenerContainerFactory")
    public void listen(Message message) {
        String type = message.getMessageProperties().getHeader("type");
        MessageType messageType = MessageType.fromName(type);
        IMessageProcessor messageProcessor = processorFactory.getMessageProcessor(messageType);
        messageProcessor.processMessage(message);
    }
}
