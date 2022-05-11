package ru.otus.lyamin.app.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lyamin.app.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(value = "book-graph")
    Optional<Book> findById(Long id);

    @EntityGraph(value = "book-graph")
    Optional<Book> findBookByTitle(String title);

    @EntityGraph(value = "book-graph")
    List<Book> findAll();


}
