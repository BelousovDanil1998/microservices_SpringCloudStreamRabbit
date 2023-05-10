package service;

import model.BookModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class BookGenerator {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 20000)
    public void generateAndSendBook() {
        BookModel newBook = createNewBook();
        rabbitTemplate.convertAndSend("bookExchange", "book.create", newBook);
    }

    private BookModel createNewBook() {
        // Зайте новую книгу с произвольными данными

        return  new BookModel(
                UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, // генерация случайного id
                "Book " + new Random().nextInt(100), // генерация случайного названия
                "Description for book ",
                new Random().nextDouble() * 100 // генерация случайной цены
        );
    }
}
