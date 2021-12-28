package ru.otus.lyamin.app.service.interf;

import ru.otus.lyamin.app.entity.Book;

import java.util.List;

public interface BookService {

    Book getBookById(Long id);

    Book getBookByTitle(String title);

    List<Book> getBooks();

    Book saveBook(String title, Long authorId, Long genreId);

    Book updateBookById(Long id, String title, Long authorId, Long genreId);

    int deleteBookById(Long id);
}
