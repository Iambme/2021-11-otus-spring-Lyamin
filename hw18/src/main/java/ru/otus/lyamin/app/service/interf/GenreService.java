package ru.otus.lyamin.app.service.interf;

import ru.otus.lyamin.app.entity.Genre;

import java.util.List;

public interface GenreService {

    Genre findById(Long id);

    Genre findByName(String name);

    List<Genre> findAll();

    Genre save(Genre genre);

    void deleteById(Long id);
}
