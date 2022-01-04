package ru.otus.lyamin.app.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.lyamin.app.entity.Genre;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static ru.otus.lyamin.app.prototype.GenrePrototype.getGenre;

@DisplayName("Класс GenreRepository должен")
@DataJpaTest
class GenreRepositoryTest {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать жанр по имени ")
    @Test
    void shouldReturnGenreByName() {
        Optional<Genre> actualGenre = genreRepository.findGenreByName(getGenre().getName());
        Genre expectedGenre = em.find(Genre.class, getGenre().getId());
        Assertions.assertThat(actualGenre).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("корректно обновлять жанр ")
    @Test
    void shouldCorrectlyUpdateGenre() {
        Genre expectedGenre = getGenre();
        expectedGenre.setName("testNewName");
        genreRepository.updateGenreNameById(getGenre().getId(), "testNewName");
        Genre actualGenre = em.find(Genre.class, getGenre().getId());
        Assertions.assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}