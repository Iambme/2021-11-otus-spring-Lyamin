package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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
    public int countOfAuthors() {
        return authorDao.countOfAuthors();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorDao.getAuthorById(id);
    }

    @Override
    public List<Author> getAuthors() {
        return authorDao.getAuthors();
    }

    @Override
    public long addAuthor(String firstName, String lastName) {
        return authorDao.addAuthor(validateAuthor(new Author(null, firstName, lastName)));
    }

    @Override
    public int updateAuthor(Long id, String firstName, String lastName) {
        return authorDao.updateAuthor(validateAuthor(new Author(id, firstName, lastName)));

    }

    @Override
    public int deleteAuthorById(long id) {
        return authorDao.deleteAuthorById(id);
    }

    private Author validateAuthor(Author author) {
        if (author.getFirstName().isEmpty() || author.getLastName().isEmpty()) {
            throw new LibraryException("Author firstname and lastname must be not empty");
        }
        return author;
    }
}
