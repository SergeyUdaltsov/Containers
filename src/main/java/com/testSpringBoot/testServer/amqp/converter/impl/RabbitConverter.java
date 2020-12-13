package com.testSpringBoot.testServer.amqp.converter.impl;

import com.testSpringBoot.testServer.amqp.converter.IRabbitConverter;
import com.testSpringBoot.testServer.utils.JsonUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

/**
 * @author Serhii_Udaltsov on 12/12/2020
 */
@Service
public class RabbitConverter implements IRabbitConverter {

    @Override
    public <T>  T unconvert(Message message, Class<T> type) {
        byte[] messageBody = message.getBody();
        return JsonUtils.parseBytes(messageBody, type);
    }

    @Override
    public <T> Message convert(T request, MessageType type) {
        String bodyString = JsonUtils.convertObjectToJson(request);
        MessageProperties properties = MessagePropertiesBuilder.newInstance().build();
        properties.setHeader("type", type.name());
        return new Message(bodyString.getBytes(Charset.defaultCharset()), properties);
    }
}
