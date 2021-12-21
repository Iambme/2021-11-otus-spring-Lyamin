package ru.otus.lyamin.app.service.interf;

import ru.otus.lyamin.app.entity.Book;

import java.util.List;

public interface BookService {
    int countOfBooks();

    Book getBookById(Long id);

    List<Book> getBooks();

    Long addBook(String title, Long authorId, Long genreId);

    int updateBook(Long id, String name, Long authorId, Long genreId);

    int deleteBookById(Long id);
}
