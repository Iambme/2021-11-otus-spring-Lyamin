package ru.otus.lyamin.app.dao.interf;

import ru.otus.lyamin.app.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Optional<Author> getAuthorById(Long id);
    List<Author> getAuthors();
    Optional<Author> getAuthorByName(String name);
    Author addAuthor(Author author);
    int updateAuthorNameById(Long id, String name);
    int deleteAuthorById(Long id);
}
