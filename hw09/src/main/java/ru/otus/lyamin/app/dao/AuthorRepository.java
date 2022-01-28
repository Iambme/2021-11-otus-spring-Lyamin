package ru.otus.lyamin.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.lyamin.app.entity.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author,String> {
    Optional<Author> findAuthorByName(String name);

}
