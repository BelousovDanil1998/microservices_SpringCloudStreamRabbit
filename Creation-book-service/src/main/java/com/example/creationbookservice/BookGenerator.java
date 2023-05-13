package com.example.creationbookservice;

import com.example.creationbookservice.BookModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@EnableScheduling
public class BookGenerator {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public BookGenerator(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private Long count = 0L;

    @Scheduled(fixedDelay = 10000)
    public void generateAndSendBook() {
        BookModel newBook = createNewBook();
        rabbitTemplate.convertAndSend("bookExchange", "bookKey", newBook);
        System.out.println("ADD book " + newBook.getId());
    }


    private BookModel createNewBook() {
        // Зайте новую книгу с произвольными данными
        return new BookModel(count++, "name", "description", "status", 12);
    }
}
