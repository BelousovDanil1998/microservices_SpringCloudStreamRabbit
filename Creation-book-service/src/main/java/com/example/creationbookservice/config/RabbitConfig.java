package com.example.creationbookservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        // Дополнительная настройка RabbitTemplate, если необходимо
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public TopicExchange bookExchange() {
        return new TopicExchange("bookExchange");
    }

    @Bean
    public Queue bookCreateQueue() {
        return new Queue("book.create");
    }

    @Bean
    public Queue bookCheckedQueue() {
        return new Queue("bookCheckedQueue");
    }

    @Bean
    public Binding binding1(TopicExchange bookExchange,
                            Queue bookCreateQueue) {
        return BindingBuilder.bind(bookCreateQueue).to(bookExchange).with("book.create");
    }

    @Bean
    public Binding binding2(TopicExchange bookExchange,
                            Queue bookCheckedQueue) {
        return BindingBuilder.bind(bookCheckedQueue).to(bookExchange).with("book.checked");
    }

}
