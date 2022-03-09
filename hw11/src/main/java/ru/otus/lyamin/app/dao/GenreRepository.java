package ru.otus.lyamin.app.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.lyamin.app.entity.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {

}
