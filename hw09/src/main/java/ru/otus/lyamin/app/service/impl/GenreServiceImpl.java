package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lyamin.app.dao.BookRepository;
import ru.otus.lyamin.app.dao.GenreRepository;
import ru.otus.lyamin.app.entity.Genre;
import ru.otus.lyamin.app.exception.LibraryException;
import ru.otus.lyamin.app.service.interf.GenreService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {
    private GenreRepository genreRepository;
    private BookRepository bookRepository;


    @Override
    @Transactional(readOnly = true)
    public Genre findById(String id) {
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
        return genreRepository.findAll();
    }

    @Override
    @Transactional
    public Genre save(String name) {
        return genreRepository.save(validateGenre(new Genre(name)));
    }

    @Override
    @Transactional
    public void updateNameById(String id, String name) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        if (genreOptional.isPresent()) {
            Genre genre = genreOptional.get();
            genre.setName(name);
            genreRepository.save(genre);
            bookRepository.updateBookGenres(id, name);
        }
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        if(!bookRepository.existsBookWithGenreId(id)) {
            genreRepository.deleteById(id);
        }
        else {
            throw new LibraryException("The library has books with the genre with id " + id);
        }
    }

    private Genre validateGenre(Genre genre) {
        if (genre.getName().isEmpty()) {
            throw new LibraryException("Genre name must be not empty");
        }
        return genre;
    }
}
