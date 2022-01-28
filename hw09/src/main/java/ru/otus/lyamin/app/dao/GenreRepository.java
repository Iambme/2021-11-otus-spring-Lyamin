package ru.otus.lyamin.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.lyamin.app.entity.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Optional<Genre> findGenreByName(String name);

}
