package com.testSpringBoot.testServer.amqp.converter;

import com.testSpringBoot.testServer.amqp.converter.impl.MessageType;
import org.springframework.amqp.core.Message;

/**
 * @author Serhii_Udaltsov on 12/12/2020
 */
public interface IRabbitConverter {

    <T> T unconvert(Message message, Class<T> type);

    <T> Message convert(T request, MessageType type);

}
