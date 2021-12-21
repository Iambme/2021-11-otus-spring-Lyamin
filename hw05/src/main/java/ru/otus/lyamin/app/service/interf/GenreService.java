package ru.otus.lyamin.app.service.interf;

import ru.otus.lyamin.app.entity.Genre;

import java.util.List;

public interface GenreService {
    int countOfGenres();

    Genre getGenreById(Long id);

    List<Genre> getGenres();

    Long addGenre(String name);

    int updateGenre(Long id, String name);

    int deleteGenreById(Long id);
}
