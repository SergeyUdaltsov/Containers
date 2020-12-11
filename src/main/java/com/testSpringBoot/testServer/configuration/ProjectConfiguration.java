package com.testSpringBoot.testServer.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.rabbitmq.client.ConnectionFactory;
import com.testSpringBoot.testServer.amqp.executor.IRabbitSender;
import com.testSpringBoot.testServer.amqp.executor.impl.RabbitSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Serhii_Udaltsov on 8/29/2020
 */
@Component
public class ProjectConfiguration {

    private final String connection;
    private final String exchangeName;
    private final String responseQueueName;
    private final String requestQueueName;


    public ProjectConfiguration(@Value("${mongodb.connection}") String connection,
                                @Value("${exchange.name}") String exchangeName,
                                @Value("${request.queue.name}") String requestQueueName,
                                @Value("${response.queue.name}") String responseQueueName) {
        this.connection = connection;
        this.exchangeName = exchangeName;
        this.requestQueueName = requestQueueName;
        this.responseQueueName = responseQueueName;
    }

    //    @Bean
    public JsonMapper jsonMapper() {
        return new JsonMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public MongoClient mongoClient() {
        ConnectionString connString = new ConnectionString(connection);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();

        return MongoClients.create(settings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "admin");
    }

//    @Bean
//    public ConnectionFactory connectionFactory() {
//        ConnectionFactory connectionFactory =
//                new ConnectionFactory();
//        connectionFactory.setHost("localhost");
//        connectionFactory.setPort(5672);
//        connectionFactory.setVirtualHost("local_vhost");
//        connectionFactory.setUsername("guest");
//        connectionFactory.setPassword("guest");
//        return connectionFactory;
//    }

//    @Bean
//    public IRabbitSender rabbitExecutor(ConnectionFactory connectionFactory) {
//        return new RabbitSender(connectionFactory, exchangeName, requestQueueName, responseQueueName);
//    }

}
