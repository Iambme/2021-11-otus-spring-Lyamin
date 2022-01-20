package ru.otus.lyamin.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.lyamin.app.dao.custom.BookRepositoryCustom;
import ru.otus.lyamin.app.entity.Book;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {
    Optional<Book> findBookByTitle(String title);
}
