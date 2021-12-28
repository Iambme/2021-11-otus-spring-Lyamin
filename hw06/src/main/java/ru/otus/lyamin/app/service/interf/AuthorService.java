package ru.otus.lyamin.app.service.interf;

import ru.otus.lyamin.app.entity.Author;

import java.util.List;

public interface AuthorService {

    Author getAuthorById(Long id);

    Author getAuthorByName(String name);

    List<Author> getAuthors();

    Author saveAuthor(String name);

    int updateAuthorNameById(Long id, String name);

    int deleteAuthorById(Long id);
}
