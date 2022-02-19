package ru.otus.lyamin.app.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.lyamin.app.entity.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

}
