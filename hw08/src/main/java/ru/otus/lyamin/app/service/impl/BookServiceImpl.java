package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lyamin.app.dao.BookRepository;
import ru.otus.lyamin.app.dao.CommentRepository;
import ru.otus.lyamin.app.entity.Author;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.entity.Comment;
import ru.otus.lyamin.app.entity.Genre;
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
    private final CommentRepository commentRepository;


    @Override
    @Transactional(readOnly = true)
    public Book findById(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new LibraryException("Book not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Book findByTitle(String title) {
        return bookRepository.findBookByTitle(title).orElseThrow(() -> new LibraryException("Book not found with title " + title));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book save(String title, String authorId, String genreId) {
        Author author = authorService.findById(authorId);
        Genre genre = genreService.findById(genreId);
        Book book = new Book(title, author, genre);
        return bookRepository.save(validateBook(book));
    }

    @Override
    @Transactional
    public Book updateById(String id, String title, String authorId, String genreId) {
        Author author = authorService.findById(authorId);
        Genre genre = genreService.findById(genreId);
        Book book = validateBook(new Book(title, author, genre));
        commentRepository.updateCommentsBook(id, book);
        return bookRepository.save(book);
    }


    @Override
    @Transactional
    public void deleteById(String id) {
        bookRepository.deleteById(id);
        List<Comment> comments = commentRepository.findCommentByBookId(id);
        commentRepository.deleteAll(comments);
    }


    private Book validateBook(Book book) {
        if (book.getTitle().isEmpty()) {
            throw new LibraryException("Book title must be not empty");
        }
        return book;
    }
}
