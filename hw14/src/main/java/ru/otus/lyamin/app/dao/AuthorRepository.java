package ru.otus.lyamin.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.lyamin.app.entity.Author;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    Optional<Author> findAuthorByName(String name);
    Set<Author> findByMongoIdIn(@Param("mongoIds") Collection<String> mongoIds);

}
