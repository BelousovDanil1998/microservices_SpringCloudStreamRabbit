package com.example.checkbookservice.service;

import com.example.checkbookservice.model.BookModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookStatusChecker {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "book.create")
    public void checkBookStatus(BookModel book) {
        book.setStatus("checked");
        rabbitTemplate.convertAndSend("bookExchange", "book.checked", book);
    }
}