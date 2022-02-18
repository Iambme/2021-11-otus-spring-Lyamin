package ru.otus.lyamin.app.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.lyamin.app.dao.custom.BookRepositoryCustom;
import ru.otus.lyamin.app.entity.Book;

import java.util.Optional;

public interface BookRepository extends ReactiveMongoRepository<Book, String>, BookRepositoryCustom {
    Optional<Book> findBookByTitle(String title);
}
