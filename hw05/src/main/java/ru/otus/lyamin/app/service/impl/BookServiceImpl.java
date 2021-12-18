package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.dao.interf.BookDao;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.exception.LibraryException;
import ru.otus.lyamin.app.service.interf.BookService;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    @Override
    public int countOfBooks() {
        return bookDao.countOfBooks();
    }

    @Override
    public Book getBookById(Long id) {
        return bookDao.getBookById(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public Long addBook(String title, Long authorId, Long genreId) {
        Book book = new Book(title, authorId, genreId);
        return bookDao.addBook(validateBook(book));
    }

    @Override
    public int updateBook(Long id, String name, Long authorId, Long genreId) {
        Book book = new Book(id, name, authorId, genreId);
        return bookDao.updateBook(validateBook(book));
    }

    @Override
    public int deleteBookById(Long id) {
        return bookDao.deleteBookById(id);
    }

    private Book validateBook(Book book) {
        if (book.getTitle().isEmpty()) {
            throw new LibraryException("Book title must be not empty");
        }
        return book;
    }
}
