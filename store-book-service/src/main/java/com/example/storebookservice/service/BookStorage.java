package com.example.storebookservice.service;

import com.example.storebookservice.model.BookEntity;
import com.example.storebookservice.model.BookModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookStorage {


    private final BookRepository bookRepository;

    @Autowired
    public BookStorage(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @RabbitListener(queues = "bookQueue2")
    public void storeBook(BookModel book) {
        BookEntity bookEntity = new BookEntity(book.getId(), book.getName(), book.getDescription(), book.getStatus(), book.getPrice());
        bookRepository.save(bookEntity);
        System.out.println("book saved " + book.getId());
    }
}