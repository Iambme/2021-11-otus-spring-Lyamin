package ru.otus.lyamin.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lyamin.app.entity.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    Optional<Author> findAuthorByName(String name);

}
