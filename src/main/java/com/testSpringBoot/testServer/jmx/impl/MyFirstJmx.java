package com.testSpringBoot.testServer.jmx.impl;

import com.testSpringBoot.testServer.jmx.MyJmxMXBean;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@ManagedResource
@Component
public class MyFirstJmx implements MyJmxMXBean {

    @ManagedOperation
    @Override
    public String getMessage() {
        System.out.println("operation invoked");
        return "hello from my jmx";
    }
}
