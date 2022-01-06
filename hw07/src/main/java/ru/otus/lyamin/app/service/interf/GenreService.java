package ru.otus.lyamin.app.service.interf;

import ru.otus.lyamin.app.entity.Genre;

import java.util.List;

public interface GenreService {

    Genre getGenreById(Long id);

    Genre getGenreByName(String name);

    List<Genre> getGenres();

    Genre saveGenre(String name);

    int updateGenreNameById(Long id, String name);

    void deleteGenreById(Long id);
}
