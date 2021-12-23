//package ru.otus.lyamin.app.dao;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
//import org.springframework.context.annotation.Import;
//import ru.otus.lyamin.app.dao.impl.GenreDaoJPA;
//import ru.otus.lyamin.app.entity.Genre;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThatCode;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static ru.otus.lyamin.app.prototype.AuthorPrototype.getAuthor;
//import static ru.otus.lyamin.app.prototype.AuthorPrototype.getDeletableAuthor;
//import static ru.otus.lyamin.app.prototype.GenrePrototype.*;
//
//@DisplayName("Класс GenreDaoJPA должен")
//@JdbcTest
//@Import(GenreDaoJPA.class)
//class GenreDaoJPATest {
//    @Autowired
//    private GenreDaoJPA genreDaoJPA;
//
//    @DisplayName("корректно возвращать количество жанров ")
//    @Test
//    void shouldReturnCorrectCountOfGenres() {
//        int expectedCount = getGenres().size();
//        int actualCount = genreDaoJPA.countOfGenres();
//        assertThat(actualCount).isEqualTo(expectedCount);
//    }
//
//    @DisplayName("возвращать жанр по id ")
//    @Test
//    void shouldReturnGenreById() {
//        Genre genre = genreDaoJPA.getGenreById(getGenre().getId());
//        Assertions.assertThat(genre).isEqualTo(getGenre());
//    }
//
//    @DisplayName("возвращать все жанры ")
//    @Test
//    void shouldReturnAllGenres() {
//        List<Genre> actualGenresList = genreDaoJPA.getGenres();
//        Assertions.assertThat(actualGenresList).usingRecursiveFieldByFieldElementComparator()
//                .containsExactlyElementsOf(getGenres());
//    }
//
//    @DisplayName("корректно добавлять жанр ")
//    @Test
//    void shouldCorrectlyAddGenre() {
//        Genre genre = new Genre(null, "testGenreName");
//        long id = genreDaoJPA.addGenre(genre);
//        Genre actualGenre = genreDaoJPA.getGenreById(id);
//        assertThat(actualGenre).isNotNull()
//                .isInstanceOf(Genre.class)
//                .hasFieldOrPropertyWithValue("name", "testGenreName");
//
//    }
//
//    @DisplayName("корректно обновлять жанр ")
//    @Test
//    void shouldCorrectlyUpdateGenre() {
//        Genre expectedGenre = getGenre();
//        expectedGenre.setName("testNewName");
//        genreDaoJPA.updateGenre(expectedGenre);
//        Genre actualGenre = genreDaoJPA.getGenreById(getAuthor().getId());
//        Assertions.assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
//    }
//
//    @DisplayName("корректно удалять жанр ")
//    @Test
//    void shouldDeleteGenreById() {
//        assertThatCode(() -> genreDaoJPA.getGenreById(getDeletableAuthor().getId())).doesNotThrowAnyException();
//        int countBeforeDelete = genreDaoJPA.getGenres().size();
//        genreDaoJPA.deleteGenreById(getDeletableGenre().getId());
//        int countAfterDelete = genreDaoJPA.countOfGenres();
//        assertThat(countBeforeDelete).isGreaterThan(countAfterDelete);
//        assertThatThrownBy(() -> genreDaoJPA.getGenreById(getDeletableAuthor().getId()));
//    }
//}