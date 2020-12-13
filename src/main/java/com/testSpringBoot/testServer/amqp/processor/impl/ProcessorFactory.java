package com.testSpringBoot.testServer.amqp.processor.impl;

import com.testSpringBoot.testServer.amqp.converter.impl.MessageType;
import com.testSpringBoot.testServer.amqp.processor.IMessageProcessor;
import com.testSpringBoot.testServer.amqp.processor.IProcessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Serhii_Udaltsov on 12/12/2020
 */
@Service
public class ProcessorFactory implements IProcessorFactory {

    private List<IMessageProcessor> processors;

    @Autowired
    public ProcessorFactory(List<IMessageProcessor> processors) {
        this.processors = processors;
    }

    @Override
    public IMessageProcessor getMessageProcessor(MessageType type) {
        IMessageProcessor processor = processors.stream()
                .filter(p -> type == p.getSupportedMessageType())
                .findFirst()
                .orElse(null);
        if (processor != null) {
            return processor;
        }
        throw new IllegalStateException("Wrong processor type: " + type.name());
    }
}
