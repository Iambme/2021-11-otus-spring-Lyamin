package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lyamin.app.dao.BookRepository;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.exception.LibraryException;
import ru.otus.lyamin.app.service.interf.AuthorService;
import ru.otus.lyamin.app.service.interf.BookService;
import ru.otus.lyamin.app.service.interf.GenreService;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    @Transactional(readOnly = true)
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new LibraryException("Book not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Book findByName(String title) {
        return bookRepository.findBookByTitle(title).orElseThrow(() -> new LibraryException("Book not found with title " + title));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book save(Book book) {
        book.setAuthor(authorService.findById(book.getAuthor().getId()));
        book.setGenre(genreService.findById(book.getGenre().getId()));
        return bookRepository.save(validateBook(book));
    }



    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }


    private Book validateBook(Book book) {
        if (book.getTitle().isEmpty()) {
            throw new LibraryException("Book title must be not empty");
        }
        return book;
    }
}
