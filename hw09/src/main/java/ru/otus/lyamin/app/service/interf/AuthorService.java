package ru.otus.lyamin.app.service.interf;

import ru.otus.lyamin.app.entity.Author;

import java.util.List;

public interface AuthorService {

    Author findById(Long id);

    Author findByName(String name);

    List<Author> findAll();

    Author save(Author author);

    void deleteById(Long id);
}
