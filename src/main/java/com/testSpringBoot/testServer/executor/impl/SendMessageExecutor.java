package com.testSpringBoot.testServer.executor.impl;

import com.testSpringBoot.testServer.amqp.executor.IRabbitSender;
import com.testSpringBoot.testServer.constants.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 12/10/2020
 */
@Component
public class SendMessageExecutor extends AbstractExecutor<Object, String> {

    private final IRabbitSender rabbitSender;

    @Autowired
    public SendMessageExecutor(IRabbitSender rabbitSender) {
        super(Object.class);
        this.rabbitSender = rabbitSender;
    }

    @Override
    String executeRequest(Object o, Map<String, String> requestParams) throws IOException {
        rabbitSender.execute("message from ui", "serg-sync");
        return "Message sent";
    }

    @Override
    public String getSupportedUri() {
        return CommonConstants.SEND_MESSAGE;
    }
}
