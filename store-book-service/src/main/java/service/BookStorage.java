package service;

import model.BookModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookStorage {
    @RabbitListener(queues = "bookCheckedQueue")
    public void storeBook(BookModel checkedBook) {
        // Здесь сохраните проверенную книгу, например, в базе данных
        


    }
}