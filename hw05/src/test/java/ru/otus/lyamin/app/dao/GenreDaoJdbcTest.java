package ru.otus.lyamin.app.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.lyamin.app.dao.impl.GenreDaoJdbc;
import ru.otus.lyamin.app.entity.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.otus.lyamin.app.prototype.AuthorPrototype.getAuthor;
import static ru.otus.lyamin.app.prototype.AuthorPrototype.getDeletableAuthor;
import static ru.otus.lyamin.app.prototype.GenrePrototype.*;

@DisplayName("Класс GenreDaoJdbc должен")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {
    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @DisplayName("корректно возвращать количество жанров ")
    @Test
    void shouldReturnCorrectCountOfGenres() {
        int expectedCount = getGenres().size();
        int actualCount = genreDaoJdbc.countOfGenres();
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @DisplayName("возвращать жанр по id ")
    @Test
    void shouldReturnGenreById() {
        Genre genre = genreDaoJdbc.getGenreById(getGenre().getId());
        Assertions.assertThat(genre).isEqualTo(getGenre());
    }

    @DisplayName("возвращать все жанры ")
    @Test
    void shouldReturnAllGenres() {
        List<Genre> actualGenresList = genreDaoJdbc.getGenres();
        Assertions.assertThat(actualGenresList).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(getGenres());
    }

    @DisplayName("корректно добавлять жанр ")
    @Test
    void shouldCorrectlyAddGenre() {
        Genre genre = new Genre(null, "testGenreName");
        long id = genreDaoJdbc.addGenre(genre);
        Genre actualGenre = genreDaoJdbc.getGenreById(id);
        assertThat(actualGenre).isNotNull()
                .isInstanceOf(Genre.class)
                .hasFieldOrPropertyWithValue("name", "testGenreName");

    }

    @DisplayName("корректно обновлять жанр ")
    @Test
    void shouldCorrectlyUpdateGenre() {
        Genre expectedGenre = getGenre();
        expectedGenre.setName("testNewName");
        genreDaoJdbc.updateGenre(expectedGenre);
        Genre actualGenre = genreDaoJdbc.getGenreById(getAuthor().getId());
        Assertions.assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("корректно удалять жанр ")
    @Test
    void shouldDeleteGenreById() {
        assertThatCode(() -> genreDaoJdbc.getGenreById(getDeletableAuthor().getId())).doesNotThrowAnyException();
        int countBeforeDelete = genreDaoJdbc.getGenres().size();
        genreDaoJdbc.deleteGenreById(getDeletableGenre().getId());
        int countAfterDelete = genreDaoJdbc.countOfGenres();
        assertThat(countBeforeDelete).isGreaterThan(countAfterDelete);
        assertThatThrownBy(() -> genreDaoJdbc.getGenreById(getDeletableAuthor().getId()));
    }
}