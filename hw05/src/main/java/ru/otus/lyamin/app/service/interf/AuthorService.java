package ru.otus.lyamin.app.service.interf;

import ru.otus.lyamin.app.entity.Author;

import java.util.List;

public interface AuthorService {
    int countOfAuthors();

    Author getAuthorById(Long id);

    List<Author> getAuthors();

    Long addAuthor(String firstName, String lastName);

    int updateAuthor(Long id, String firstName, String lastName);

    int deleteAuthorById(Long id);
}
