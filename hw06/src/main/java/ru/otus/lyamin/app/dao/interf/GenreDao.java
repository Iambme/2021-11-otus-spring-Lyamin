package ru.otus.lyamin.app.dao.interf;

import ru.otus.lyamin.app.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    Optional<Genre> getGenreById(Long id);

    Optional<Genre> getGenreByName(String name);

    List<Genre> getGenres();

    Genre saveGenre(Genre genre);

    int updateGenreNameById(Long id, String name);

    int deleteGenreById(Long id);
}
