package ru.otus.lyamin.app.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.lyamin.app.entity.Book;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(value = "book-graph")
    Optional<Book> findById(Long id);

    @EntityGraph(value = "book-graph")
    Optional<Book> findBookByName(String name);

    @EntityGraph(value = "book-graph")
    List<Book> findAll();

    Set<Book> findByMongoIdIn(@Param("mongoIds") Collection<String> mongoIds);

}
