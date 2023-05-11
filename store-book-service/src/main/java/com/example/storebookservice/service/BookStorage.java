package com.example.storebookservice.service;

import com.example.storebookservice.model.BookModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookStorage {
    private static final String INSERT_SQL = "INSERT INTO rabbitdb (id, name, description, status, price) VALUES (?, ?, ?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

    public BookStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RabbitListener(queues = "bookCheckedQueue")
    public void storeBook(BookModel checkedBook) {
        jdbcTemplate.update(INSERT_SQL,
                checkedBook.getId(),
                checkedBook.getName(),
                checkedBook.getDescription(),
                checkedBook.getStatus(),
                checkedBook.getPrice()
        );
    }
}