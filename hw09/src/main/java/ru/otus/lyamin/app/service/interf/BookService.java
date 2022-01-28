package ru.otus.lyamin.app.service.interf;

import ru.otus.lyamin.app.entity.Book;

import java.util.List;

public interface BookService {

    Book findById(String id);

    Book findByTitle(String title);

    List<Book> findAll();

    Book save(Book book);

    void deleteById(String id);
}
