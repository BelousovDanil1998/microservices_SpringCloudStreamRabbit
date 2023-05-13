package com.example.creationbookservice;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.function.Supplier;


@SpringBootApplication
@EnableScheduling
public class CreationBookServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreationBookServiceApplication.class, args);
    }

    static class Publisher {
        private Long count = 0L;

        @Bean
        public Supplier<BookModel> publish() {
            return () -> {
                BookModel bookModel = new BookModel(count++, "name", "description", "default_status", 12);
                System.out.println("Send Book " + bookModel.getId());
                return bookModel;
            };
        }
    }
}