package ru.otus.lyamin.app.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lyamin.app.dao.GenreRepository;
import ru.otus.lyamin.app.entity.Genre;
import ru.otus.lyamin.app.service.impl.GenreServiceImpl;
import ru.otus.lyamin.app.service.interf.GenreService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static ru.otus.lyamin.app.prototype.GenrePrototype.getGenre;
import static ru.otus.lyamin.app.prototype.GenrePrototype.getGenres;

@DisplayName("Класс GenreServiceImpl должен")
@SpringBootTest(classes = GenreServiceImpl.class)
class GenreServiceImplTest {
    @MockBean
    private GenreRepository genreRepository;
    @Autowired
    private GenreService genreService;


    @DisplayName("возвращать жанр по id ")
    @Test
    void shouldReturnGenreById() {
        Genre expectedGenre = getGenre();
        when(genreRepository.findById(expectedGenre.getId())).thenReturn(Optional.of(expectedGenre));
        Genre actualGenre = genreService.findById(expectedGenre.getId());
        assertThat(actualGenre).isNotNull()
                .isInstanceOf(Genre.class)
                .usingRecursiveComparison()
                .isEqualTo(expectedGenre);
        verify(genreRepository, times(1)).findById(expectedGenre.getId());
    }

    @DisplayName("возвращать жанр по имени ")
    @Test
    void shouldReturnGenreByName() {
        Genre expectedGenre = getGenre();
        when(genreRepository.findGenreByName(expectedGenre.getName())).thenReturn(Optional.of(expectedGenre));
        Genre actualGenre = genreService.findByName(expectedGenre.getName());
        assertThat(actualGenre).isNotNull()
                .isInstanceOf(Genre.class)
                .usingRecursiveComparison()
                .isEqualTo(expectedGenre);
        verify(genreRepository, times(1)).findGenreByName(expectedGenre.getName());
    }

    @DisplayName("возвращать всех жанров ")
    @Test
    void shouldReturnAllGenres() {
        List<Genre> expectedGenres = getGenres();
        when(genreRepository.findAll()).thenReturn(expectedGenres);
        List<Genre> actualGenres = genreService.findAll();
        assertThat(actualGenres).isNotEmpty()
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(expectedGenres);
        verify(genreRepository, times(1)).findAll();
    }

    @DisplayName("корректно добавлять жанр ")
    @Test
    void shouldCorrectlyAddGenre() {
        when(genreRepository.save(any(Genre.class))).thenReturn((getGenre()));
        Genre actualGenre = genreService.save(getGenre());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(getGenre());
        verify(genreRepository, times(1)).save(any(Genre.class));
    }

    @DisplayName("корректно обновлять жанр ")
    @Test
    void shouldCorrectlyUpdateGenre() {
        Genre expectedGenre = getGenre();
        when(genreRepository.save(expectedGenre)).thenReturn(expectedGenre);
        Genre actualGenre = genreService.save(expectedGenre);
        assertThat(actualGenre).isEqualTo(expectedGenre);
        verify(genreRepository, times(1)).save(expectedGenre);
    }

    @DisplayName("корректно удалять жанр ")
    @Test
    void shouldDeleteGenreById() {
        genreService.deleteById(getGenre().getId());
        verify(genreRepository, times(1)).deleteById(getGenre().getId());
    }
}