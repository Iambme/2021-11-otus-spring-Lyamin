package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(readOnly = true)
    public Genre getGenreById(Long id) {
        return genreDao.getGenreById(id).orElseThrow(() -> new LibraryException("Genre not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getGenreByName(String name) {
        return genreDao.getGenreByName(name).orElseThrow(() -> new LibraryException("Genre not found with name " + name));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getGenres() {
        return genreDao.getGenres();
    }

    @Override
    @Transactional
    public Genre saveGenre(String name) {
        return genreDao.saveGenre(validateGenre(new Genre(name)));
    }

    @Override
    @Transactional
    public int updateGenreNameById(Long id, String name) {
        return genreDao.updateGenreNameById(id, name);
    }

    @Override
    @Transactional
    public int deleteGenreById(Long id) {
        return genreDao.deleteGenreById(id);
    }

    private Genre validateGenre(Genre genre) {
        if (genre.getName().isEmpty()) {
            throw new LibraryException("Genre name must be not empty");
        }
        return genre;
    }
}
