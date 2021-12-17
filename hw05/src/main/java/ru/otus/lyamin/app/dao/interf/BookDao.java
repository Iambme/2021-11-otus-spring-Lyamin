package ru.otus.lyamin.app.dao.interf;

import ru.otus.lyamin.app.entity.Book;

import java.util.List;

public interface BookDao {
    int countOfBooks();

    Book getBookById(long id);

    List<Book> getBooks();

    long addBook(Book book);

    int updateBook(Book book);

    int deleteBookById(long id);
}
