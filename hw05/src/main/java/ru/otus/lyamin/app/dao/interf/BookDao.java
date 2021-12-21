package ru.otus.lyamin.app.dao.interf;

import ru.otus.lyamin.app.entity.Book;

import java.util.List;

public interface BookDao {
    int countOfBooks();

    Book getBookById(Long id);

    List<Book> getBooks();

    Long addBook(Book book);

    int updateBook(Book book);

    int deleteBookById(Long id);
}
