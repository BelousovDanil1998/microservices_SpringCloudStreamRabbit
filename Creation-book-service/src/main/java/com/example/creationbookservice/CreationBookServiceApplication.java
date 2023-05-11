package com.example.creationbookservice;


import com.example.creationbookservice.config.RabbitConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@Import(RabbitConfig.class)
public class CreationBookServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreationBookServiceApplication.class, args);
    }

}