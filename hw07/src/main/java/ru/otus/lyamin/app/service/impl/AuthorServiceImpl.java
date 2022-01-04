package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lyamin.app.dao.AuthorRepository;
import ru.otus.lyamin.app.entity.Author;
import ru.otus.lyamin.app.exception.LibraryException;
import ru.otus.lyamin.app.service.interf.AuthorService;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;


    @Override
    @Transactional(readOnly = true)
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new LibraryException("Author not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Author getAuthorByName(String name) {
        return authorRepository.findAuthorByName(name).orElseThrow(() -> new LibraryException("Author not found with name " + name));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public Author saveAuthor(String name) {
        return authorRepository.save(validateAuthor(new Author(name)));
    }

    @Override
    @Transactional
    public int updateAuthorNameById(Long id, String authorName) {
        return authorRepository.updateNameById(id, authorName);

    }

    @Override
    @Transactional
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    private Author validateAuthor(Author author) {
        if (author.getName().isEmpty()) {
            throw new LibraryException("Author name must be not empty");
        }
        return author;
    }
}
