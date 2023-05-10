package com.example.creationbookservice;

import model.BookModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

@SpringBootApplication
public class CreationBookServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreationBookServiceApplication.class, args);
    }

    @Bean
    public Supplier<BookModel> sendNewBooks() {
        return () -> new BookModel(
                UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, // генерация случайного id
                "Book " + new Random().nextInt(100), // генерация случайного названия
                "Description for book ",
                new Random().nextDouble() * 100 // генерация случайной цены
        );
    }
}