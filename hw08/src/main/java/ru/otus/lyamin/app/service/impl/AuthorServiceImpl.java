package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lyamin.app.dao.AuthorRepository;
import ru.otus.lyamin.app.dao.BookRepository;
import ru.otus.lyamin.app.entity.Author;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.exception.LibraryException;
import ru.otus.lyamin.app.service.interf.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;


    @Override
    @Transactional(readOnly = true)
    public Author findById(String id) {
        return authorRepository.findById(id).orElseThrow(() -> new LibraryException("Author not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Author findByName(String name) {
        return authorRepository.findAuthorByName(name).orElseThrow(() -> new LibraryException("Author not found with name " + name));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public Author save(String name) {
        return authorRepository.save(validateAuthor(new Author(name)));
    }

    @Override
    @Transactional
    public void updateNameById(String id, String authorName) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setName(authorName);
            validateAuthor(author);
            authorRepository.save(author);
            bookRepository.updateBookAuthors(id, authorName);
        }
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        authorRepository.deleteById(id);
        List<Book> books = bookRepository.findByAuthorId(id);
        books.forEach(book -> book.setAuthor(null));
        bookRepository.saveAll(books);
    }

    private Author validateAuthor(Author author) {
        if (author.getName().isEmpty()) {
            throw new LibraryException("Author name must be not empty");
        }
        return author;
    }
}
