package ru.otus.lyamin.app.dao.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.lyamin.app.entity.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.otus.lyamin.app.prototype.GenrePrototype.*;

@DisplayName("Класс GenreDaoJPA должен")
@DataJpaTest
@Import(GenreDaoJPA.class)
class GenreDaoJPATest {
    @Autowired
    private GenreDaoJPA genreDaoJPA;

    @Autowired
    private TestEntityManager em;


    @DisplayName("возвращать жанр по id ")
    @Test
    void shouldReturnGenreById() {
        Optional<Genre> actualGenre = genreDaoJPA.getGenreById(getGenre().getId());
        Genre expectedGenre = em.find(Genre.class, getGenre().getId());
        Assertions.assertThat(actualGenre).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать жанр по имени ")
    @Test
    void shouldReturnGenreByName() {
        Optional<Genre> actualGenre = genreDaoJPA.getGenreByName(getGenre().getName());
        Genre expectedGenre = em.find(Genre.class, getGenre().getId());
        Assertions.assertThat(actualGenre).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать все жанры ")
    @Test
    void shouldReturnAllGenres() {
        List<Genre> actualGenreList = genreDaoJPA.getGenres();
        Assertions.assertThat(actualGenreList).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(getGenres());
    }

    @DisplayName("корректно добавлять жанр ")
    @Test
    void shouldCorrectlyAddGenre() {
        Genre Genre = new Genre(null, "testGenreName");
        Genre actualGenre = genreDaoJPA.saveGenre(Genre);
        assertThat(actualGenre).isNotNull()
                .isInstanceOf(Genre.class)
                .hasFieldOrPropertyWithValue("name", "testGenreName");

    }

    @DisplayName("корректно обновлять жанр ")
    @Test
    void shouldCorrectlyUpdateGenre() {
        Genre expectedGenre = getGenre();
        expectedGenre.setName("testNewName");
        genreDaoJPA.updateGenreNameById(getGenre().getId(), "testNewName");
        Genre actualGenre = em.find(Genre.class, getGenre().getId());
        Assertions.assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("корректно удалять жанр ")
    @Test
    void shouldDeleteGenreById() {
        Genre genre = em.find(Genre.class, getDeletableGenre().getId());
        Assertions.assertThat(genre).isNotNull();
        em.detach(genre);
        genreDaoJPA.deleteGenreById(getDeletableGenre().getId());
        genre = em.find(Genre.class, getDeletableGenre().getId());
        Assertions.assertThat(genre).isNull();
    }
}