package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lyamin.app.dao.interf.AuthorDao;
import ru.otus.lyamin.app.entity.Author;
import ru.otus.lyamin.app.exception.LibraryException;
import ru.otus.lyamin.app.service.interf.AuthorService;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private AuthorDao authorDao;


    @Override
    @Transactional(readOnly = true)
    public Author getAuthorById(Long id) {
        return authorDao.getAuthorById(id).orElseThrow(() -> new LibraryException("Author not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Author getAuthorByName(String name) {
        return authorDao.getAuthorByName(name).orElseThrow(() -> new LibraryException("Author not found with name " + name));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAuthors() {
        return authorDao.getAuthors();
    }

    @Override
    @Transactional
    public Author addAuthor(String name) {
        return authorDao.addAuthor(validateAuthor(new Author(name)));
    }

    @Override
    @Transactional
    public int updateAuthorNameById(Long id, String authorName) {
        return authorDao.updateAuthorNameById(id, authorName);

    }

    @Override
    @Transactional
    public int deleteAuthorById(Long id) {
        return authorDao.deleteAuthorById(id);
    }

    private Author validateAuthor(Author author) {
        if (author.getName().isEmpty()) {
            throw new LibraryException("Author name must be not empty");
        }
        return author;
    }
}
