package ru.otus.lyamin.app.dao.interf;

import ru.otus.lyamin.app.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Optional<Book> getBookById(Long id);
    Optional<Book> getBookByTitle(String title);
    List<Book> getBooks();
    Book addBook(Book book);
    int updateBookTitleById(Long id, String title, Long authorId, Long genreId);
    int deleteBookById(Long id);
}
