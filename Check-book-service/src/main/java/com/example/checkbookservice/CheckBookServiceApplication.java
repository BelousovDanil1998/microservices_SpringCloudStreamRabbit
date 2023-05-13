package com.example.checkbookservice;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.function.Function;

@SpringBootApplication
public class CheckBookServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckBookServiceApplication.class, args);
    }

    static class Processor {
        @Bean
        public Function<BookModel, BookModel> process() {
            return book -> {
                book.setStatus("checked");
                System.out.println("check book " + book.getId());
                return book;
            };
        }
    }

}
