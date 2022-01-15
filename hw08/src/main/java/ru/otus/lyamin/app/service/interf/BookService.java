package ru.otus.lyamin.app.service.interf;

import ru.otus.lyamin.app.entity.Book;

import java.util.List;

public interface BookService {

    Book findById(String id);

    Book findByTitle(String title);

    List<Book> findAll();

    Book save(String title, String authorId, String genreId);

    Book updateById(String id, String title, String authorId, String genreId);

    void deleteById(String id);
}
