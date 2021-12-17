package ru.otus.lyamin.app.dao.interf;

import ru.otus.lyamin.app.entity.Genre;

import java.util.List;

public interface GenreDao {
    int countOfGenres();

    Genre getGenreById(Long id);

    List<Genre> getGenres();

    long addGenre(Genre genre);

    int updateGenre(Genre genre);

    int deleteGenreById(Long id);
}
