package com.testSpringBoot.testServer.jmx.configuration;

import com.testSpringBoot.testServer.jmx.MyJmxMBean;
import com.testSpringBoot.testServer.jmx.impl.MyFirstJmx;
import com.testSpringBoot.testServer.jmx.impl.MyJmx;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@Configuration
public class BaseConfiguration {

    @Bean
    public MBeanServer mBeanServer() {
        return ManagementFactory.getPlatformMBeanServer();
    }

    @Bean
    public MyFirstJmx jmxMBean(MBeanServer mBeanServer) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        ObjectName objectName = new ObjectName("com.testSpringBoot.testServer.jmx.impl:type=MyJmx");
        MyFirstJmx myFirstJmx = new MyFirstJmx();
        mBeanServer.registerMBean(myFirstJmx, objectName);
        return myFirstJmx;
    }

}
