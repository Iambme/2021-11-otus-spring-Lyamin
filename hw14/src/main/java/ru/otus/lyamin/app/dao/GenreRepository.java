package ru.otus.lyamin.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.lyamin.app.entity.Genre;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findGenreByName(String name);
    Set<Genre> findByMongoIdIn(@Param("mongoIds") Collection<String> mongoIds);

}
