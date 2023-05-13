package com.example.storebookservice;

import com.example.storebookservice.model.BookEntity;
import com.example.storebookservice.model.BookModel;
import com.example.storebookservice.service.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class StoreBookServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreBookServiceApplication.class, args);
    }

    static class Subscriber {

        private final BookRepository bookRepository;

        Subscriber(BookRepository bookRepository) {
            this.bookRepository = bookRepository;
        }

        @Bean
        public Consumer<BookModel> subscribe() {
            return book -> {
                BookEntity bookEntity = new BookEntity(book.getId(), book.getName(), book.getDescription(), book.getStatus(), book.getPrice());
                bookRepository.save(bookEntity);
                System.out.println("book saved " + book.getId());
            };
        }
    }
}
