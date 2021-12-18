package ru.otus.lyamin.app.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lyamin.app.dao.interf.GenreDao;
import ru.otus.lyamin.app.entity.Genre;
import ru.otus.lyamin.app.service.interf.GenreService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static ru.otus.lyamin.app.prototype.GenrePrototype.getGenre;
import static ru.otus.lyamin.app.prototype.GenrePrototype.getGenres;

@DisplayName("Класс GenreServiceImpl должен")
@SpringBootTest(classes = GenreServiceImpl.class)
class GenreServiceImplTest {
    @MockBean
    private GenreDao genreDao;
    @Autowired
    private GenreService genreService;


    @DisplayName("корректно возвращать количество жанров ")
    @Test
    void shouldReturnCorrectCountOfGenres() {
        int expectedCount = getGenres().size();
        when(genreDao.countOfGenres()).thenReturn(getGenres().size());
        int actualCount = genreService.countOfGenres();
        assertThat(actualCount).isEqualTo(expectedCount);
        verify(genreDao, times(1)).countOfGenres();
    }

    @DisplayName("возвращать жанр по id ")
    @Test
    void shouldReturnGenreById() {
        Genre expectedGenre = getGenre();
        when(genreDao.getGenreById(expectedGenre.getId())).thenReturn(expectedGenre);
        Genre actualGenre = genreService.getGenreById(expectedGenre.getId());
        assertThat(actualGenre).isNotNull()
                .isInstanceOf(Genre.class)
                .usingRecursiveComparison()
                .isEqualTo(expectedGenre);
        verify(genreDao, times(1)).getGenreById(expectedGenre.getId());
    }

    @DisplayName("возвращать всех жанров ")
    @Test
    void shouldReturnAllGenres() {
        List<Genre> expectedGenres = getGenres();
        when(genreDao.getGenres()).thenReturn(expectedGenres);
        List<Genre> actualGenres = genreService.getGenres();
        assertThat(actualGenres).isNotEmpty()
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(expectedGenres);
        verify(genreDao, times(1)).getGenres();
    }

    @DisplayName("корректно добавлять жанр ")
    @Test
    void shouldCorrectlyAddGenre() {
        Genre testGenre = getGenre();
        long expectedId = testGenre.getId();
        testGenre.setId(null);
        when(genreDao.addGenre(testGenre)).thenReturn(expectedId);
        long actualId = genreService.addGenre(testGenre.getName());
        assertThat(actualId).isEqualTo(expectedId);
        verify(genreDao, times(1)).addGenre(testGenre);
    }

    @DisplayName("корректно обновлять жанр ")
    @Test
    void shouldCorrectlyUpdateGenre() {
        int expectedCount = 1;
        when(genreDao.updateGenre(getGenre())).thenReturn(expectedCount);
        int actualCount = genreService.updateGenre(getGenre().getId(), getGenre().getName());
        assertThat(actualCount).isEqualTo(expectedCount);
        verify(genreDao, times(1)).updateGenre(getGenre());
    }

    @DisplayName("корректно удалять жанр ")
    @Test
    void shouldDeleteGenreById() {
        int expectedCount = 1;
        when(genreDao.deleteGenreById(getGenre().getId())).thenReturn(expectedCount);
        int actualCount = genreService.deleteGenreById(getGenre().getId());
        assertThat(actualCount).isEqualTo(expectedCount);
        verify(genreDao, times(1)).deleteGenreById(getGenre().getId());
    }
}