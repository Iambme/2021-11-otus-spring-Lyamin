package ru.otus.lyamin.app.service.interf;

import ru.otus.lyamin.app.entity.Book;

import java.util.List;

public interface BookService {

    Book getBookById(Long id);

    Book getBookByTitle(String title);

    List<Book> getBooks();

    Book addBook(String title, Long authorId, Long genreId);

    int updateBookById(Long id, String title, Long authorId, Long genreId);

    int deleteBookById(Long id);
}
