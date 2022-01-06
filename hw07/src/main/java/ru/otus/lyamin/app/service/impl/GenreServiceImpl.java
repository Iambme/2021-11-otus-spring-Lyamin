package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lyamin.app.dao.GenreRepository;
import ru.otus.lyamin.app.entity.Genre;
import ru.otus.lyamin.app.exception.LibraryException;
import ru.otus.lyamin.app.service.interf.GenreService;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {
    private GenreRepository genreRepository;


    @Override
    @Transactional(readOnly = true)
    public Genre getGenreById(Long id) {
        return genreRepository.findById(id).orElseThrow(() -> new LibraryException("Genre not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getGenreByName(String name) {
        return genreRepository.findGenreByName(name).orElseThrow(() -> new LibraryException("Genre not found with name " + name));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    @Override
    @Transactional
    public Genre saveGenre(String name) {
        return genreRepository.save(validateGenre(new Genre(name)));
    }

    @Override
    @Transactional
    public int updateGenreNameById(Long id, String name) {
        return genreRepository.updateGenreNameById(id, name);
    }

    @Override
    @Transactional
    public void deleteGenreById(Long id) {
        genreRepository.deleteById(id);
    }

    private Genre validateGenre(Genre genre) {
        if (genre.getName().isEmpty()) {
            throw new LibraryException("Genre name must be not empty");
        }
        return genre;
    }
}
