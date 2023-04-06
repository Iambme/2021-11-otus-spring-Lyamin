package ru.otus.lyamin.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lyamin.app.entity.Genre;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findGenreByName(String name);

}
