package ru.otus.lyamin.app.service.interf;

import ru.otus.lyamin.app.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book getBookById(Long id);
    Book getBookByTitle(String title);
    List<Book> getBooks();
    Book addBook(String title, Long authorId, Long genreId);
    int updateBookTitleById(Long id, String title, Long authorId,Long genreId);
    int deleteBookById(Long id);
}
