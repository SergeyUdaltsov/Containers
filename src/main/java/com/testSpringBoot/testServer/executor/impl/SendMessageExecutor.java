package com.testSpringBoot.testServer.executor.impl;

import com.testSpringBoot.testServer.amqp.converter.IRabbitConverter;
import com.testSpringBoot.testServer.amqp.converter.impl.MessageType;
import com.testSpringBoot.testServer.amqp.executor.IRabbitExecutor;
import com.testSpringBoot.testServer.constants.CommonConstants;
import com.testSpringBoot.testServer.model.Gender;
import com.testSpringBoot.testServer.model.User;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 12/10/2020
 */
@Component
public class SendMessageExecutor extends AbstractExecutor<Object, String> {

    private final IRabbitExecutor rabbitExecutor;
    private final IRabbitConverter rabbitConverter;
    private String routingKey;

    @Autowired
    public SendMessageExecutor(IRabbitExecutor rabbitSender, IRabbitConverter rabbitConverter,
                               @Value("${routing.key.second}") String routingKey) {
        super(Object.class);
        this.rabbitExecutor = rabbitSender;
        this.rabbitConverter = rabbitConverter;
        this.routingKey = routingKey;
    }

    @Override
    String executeRequest(Object o, Map<String, String> requestParams) throws IOException {
        User user = new User();
        user.setEmail("email@gmail.com");
        user.setBirthday(55656516);
        user.setGender(Gender.MALE);
        Message message = rabbitConverter.convert(user, MessageType.USER);
        rabbitExecutor.send(message, routingKey);
        return "Message sent";
    }

    @Override
    public String getSupportedUri() {
        return CommonConstants.SEND_MESSAGE;
    }
}
