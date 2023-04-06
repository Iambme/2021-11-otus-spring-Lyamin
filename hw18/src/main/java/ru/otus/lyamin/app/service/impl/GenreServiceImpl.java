package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lyamin.app.dao.GenreRepository;
import ru.otus.lyamin.app.entity.Genre;
import ru.otus.lyamin.app.exception.LibraryException;
import ru.otus.lyamin.app.service.interf.GenreService;

import java.util.List;

import static ru.otus.lyamin.app.util.ProblemsGenerator.generateProblem;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {
    private GenreRepository genreRepository;


    @Override
    @Transactional(readOnly = true)
    public Genre findById(Long id) {
        return genreRepository.findById(id).orElseThrow(() -> new LibraryException("Genre not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Genre findByName(String name) {
        return genreRepository.findGenreByName(name).orElseThrow(() -> new LibraryException("Genre not found with name " + name));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        generateProblem("service.GenreServiceImpl.findAll");
        return genreRepository.findAll();
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        return genreRepository.save(validateGenre(genre));
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }

    private Genre validateGenre(Genre genre) {
        if (genre.getName().isEmpty()) {
            throw new LibraryException("Genre name must be not empty");
        }
        return genre;
    }
}
