package com.testSpringBoot.testServer.configuration;


import com.testSpringBoot.testServer.amqp.executor.IRabbitExecutor;
import com.testSpringBoot.testServer.amqp.executor.impl.RabbitExecutor;
import com.testSpringBoot.testServer.amqp.processor.IProcessorFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Serhii_Udaltsov on 12/11/2020
 */
@Configuration
public class RabbitConfiguration {

    @Bean
    public AmqpTemplate rabbitAmqpTemplate(ConnectionFactory connectionFactory,
                                           MessageConverter messageConverter) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setRoutingKey("serg-sync");
        rabbitTemplate.setExchange("serg-exchange");
        return rabbitTemplate;
    }

    @Bean
    public IRabbitExecutor rabbitExecutor(AmqpTemplate rabbitAmqpTemplate,
                                          IProcessorFactory processorFactory) {
        return new RabbitExecutor(rabbitAmqpTemplate, processorFactory);
    }

    @Bean
    ConnectionFactory connectionFactory(@Value("${rabbit.host}") String rabbitHost,
                                        @Value("${rabbit.virtual.host}") String virtHost,
                                        @Value("${rabbit.port}") int rabbitPort) {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(rabbitHost, rabbitPort);
        connectionFactory.setVirtualHost(virtHost);
        return connectionFactory;
    }

    @Bean("listenerContainerFactory")
    public SimpleRabbitListenerContainerFactory ownershipListenerContainer(ConnectionFactory connectionFactory,
                                                                           ContentTypeDelegatingMessageConverter contentTypeDelegatingMessageConverter) {
        SimpleRabbitListenerContainerFactory container = new SimpleRabbitListenerContainerFactory();
        container.setConnectionFactory(connectionFactory);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setDefaultRequeueRejected(false);
        container.setMessageConverter(contentTypeDelegatingMessageConverter);
        container.setAutoStartup(true);
        return container;
    }

    @Bean
    public ContentTypeDelegatingMessageConverter messageConverter(Jackson2JsonMessageConverter jackson2JsonMessageConverter,
                                                                  SimpleMessageConverter simpleMessageConverter) {
        ContentTypeDelegatingMessageConverter contentTypeDelegatingMessageConverter = new ContentTypeDelegatingMessageConverter();
        contentTypeDelegatingMessageConverter.addDelegate("application/json", jackson2JsonMessageConverter);
        contentTypeDelegatingMessageConverter.addDelegate("text/plain", simpleMessageConverter);
        return contentTypeDelegatingMessageConverter;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleMessageConverter simpleMessageConverter() {
        return new SimpleMessageConverter();
    }

}
