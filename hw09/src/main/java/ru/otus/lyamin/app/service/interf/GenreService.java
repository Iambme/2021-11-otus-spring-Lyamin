package ru.otus.lyamin.app.service.interf;

import ru.otus.lyamin.app.entity.Genre;

import java.util.List;

public interface GenreService {

    Genre findById(String id);

    Genre findByName(String name);

    List<Genre> findAll();

    Genre save(String name);

    void updateNameById(String id, String name);

    void deleteById(String id);
}
