package ru.otus.lyamin.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.lyamin.app.entity.Genre;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findGenreByName(String name);

    @Modifying
    @Query("update Genre a set a.name = :name where a.id = :id")
    int updateGenreNameById(@Param("id") Long id, @Param("name") String name);
}
