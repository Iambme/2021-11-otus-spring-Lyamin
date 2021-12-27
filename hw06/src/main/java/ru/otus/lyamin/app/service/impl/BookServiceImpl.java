package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lyamin.app.dao.interf.BookDao;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.exception.LibraryException;
import ru.otus.lyamin.app.service.interf.BookService;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;


    @Override
    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return bookDao.getBookById(id).orElseThrow(() -> new LibraryException("Book not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookByTitle(String title) {
        return bookDao.getBookByTitle(title).orElseThrow(() -> new LibraryException("Book not found with title " + title));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    @Transactional
    public Book addBook(String title, Long authorId, Long genreId) {
        Book book = new Book(title, authorId, genreId);
        return bookDao.addBook(validateBook(book));
    }

    @Override
    @Transactional
    public int updateBookById(Long id, String title, Long authorId, Long genreId) {
        return bookDao.updateBookById(id, title, authorId, genreId);
    }


    @Override
    @Transactional
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
