package com.testSpringBoot.testServer.amqp.converter.impl;

import com.testSpringBoot.testServer.model.User;

/**
 * @author Serhii_Udaltsov on 12/12/2020
 */
public enum MessageType {
    USER(User.class),
    STRING(String.class);

    private Class clazz;

    MessageType(Class clazz) {
        this.clazz = clazz;
    }

    public static MessageType fromName(String name) {
        for (MessageType value : MessageType.values()) {
            if (name.equalsIgnoreCase(value.name())) {
                return value;
            }
        }
        throw new IllegalStateException("Wrong message type: " + name);
    }

    public Class getClazz() {
        return clazz;
    }
}
