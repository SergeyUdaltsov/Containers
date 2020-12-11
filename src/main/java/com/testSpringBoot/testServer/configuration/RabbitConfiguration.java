package com.testSpringBoot.testServer.configuration;


import com.testSpringBoot.testServer.amqp.executor.IRabbitSender;
import com.testSpringBoot.testServer.amqp.executor.impl.RabbitSender;
import com.testSpringBoot.testServer.amqp.executor.impl.SpringBootRabbitListener;
import com.testSpringBoot.testServer.amqp.executor.impl.SpringBootRabbitSender;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Serhii_Udaltsov on 12/11/2020
 */
@Configuration
public class RabbitConfiguration {

    @Bean
    Queue queue(@Value("${request.queue.name}") String queueName){
        return new Queue(queueName, false);
    }

    @Bean
    DirectExchange exchange(@Value("${exchange.name}") String exchange) {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding(@Value("${routing.key}") String routingKey, Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    ConnectionFactory connectionFactory(@Value("${rabbit.user.name}") String userName,
                                        @Value("${rabbit.password}") String password) {
        return new CachingConnectionFactory("localhost",
                5672);
    }


    @Bean
    public AmqpTemplate rabbitAmqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public IRabbitSender rabbitExecutor(AmqpTemplate rabbitTemplate,
                                        @Value("${exchange.name}") String exchange) {
        return new SpringBootRabbitSender(rabbitTemplate, exchange);
    }

    @Bean
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory, Queue queue) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueues(queue);
        simpleMessageListenerContainer.setMessageListener(new SpringBootRabbitListener());
        return simpleMessageListenerContainer;

    }
}
