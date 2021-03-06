package ru.otus.lyamin.app.dao.interf;

import ru.otus.lyamin.app.entity.Author;

import java.util.List;

public interface AuthorDao {
    int countOfAuthors();

    Author getAuthorById(Long id);

    List<Author> getAuthors();

    Long addAuthor(Author author);

    int updateAuthor(Author author);

    int deleteAuthorById(Long id);
}
