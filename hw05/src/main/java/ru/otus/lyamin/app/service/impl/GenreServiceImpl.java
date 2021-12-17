package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.dao.interf.GenreDao;
import ru.otus.lyamin.app.entity.Genre;
import ru.otus.lyamin.app.exception.LibraryException;
import ru.otus.lyamin.app.service.interf.GenreService;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {
    private GenreDao genreDao;

    @Override
    public int countOfGenres() {
        return genreDao.countOfGenres();
    }

    @Override
    public Genre getGenreById(long id) {
        return genreDao.getGenreById(id);
    }

    @Override
    public List<Genre> getGenres() {
        return genreDao.getGenres();
    }

    @Override
    public long addGenre(String name) {
        return genreDao.addGenre(validateGenre(new Genre(null, name)));
    }

    @Override
    public int updateGenre(Long id, String name) {
        return genreDao.updateGenre(validateGenre(new Genre(id, name)));
    }

    @Override
    public int deleteGenreById(long id) {
        return genreDao.deleteGenreById(id);
    }

    private Genre validateGenre(Genre genre) {
        if (genre.getName().isEmpty()) {
            throw new LibraryException("Genre name must be not empty");
        }
        return genre;
    }
}
