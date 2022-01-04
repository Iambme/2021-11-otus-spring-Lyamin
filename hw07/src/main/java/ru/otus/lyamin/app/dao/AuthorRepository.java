package ru.otus.lyamin.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.lyamin.app.entity.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    Optional<Author> findAuthorByName(String name);

    @Modifying
    @Query("update Author a set a.name = :name where a.id = :id")
    int updateNameById(@Param("id") Long id, @Param("name") String name);
}
