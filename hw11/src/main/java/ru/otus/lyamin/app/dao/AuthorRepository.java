package ru.otus.lyamin.app.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.lyamin.app.entity.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author,String> {

}
