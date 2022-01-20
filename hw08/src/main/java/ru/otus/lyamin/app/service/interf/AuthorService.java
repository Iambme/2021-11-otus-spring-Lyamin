package ru.otus.lyamin.app.service.interf;

import ru.otus.lyamin.app.entity.Author;

import java.util.List;

public interface AuthorService {

    Author findById(String id);

    Author findByName(String name);

    List<Author> findAll();

    Author save(String name);

    void updateNameById(String id, String name);

    void deleteById(String id);
}
