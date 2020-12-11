package com.testSpringBoot.testServer.jmx.impl;

import com.testSpringBoot.testServer.jmx.MyJmxMBean;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@ManagedResource
@Component
public class MyJmx implements MyJmxMBean {

    @ManagedOperation
    @Override
    public String getMessage() {
        return "Hello from my first jmx controller";
    }
}
