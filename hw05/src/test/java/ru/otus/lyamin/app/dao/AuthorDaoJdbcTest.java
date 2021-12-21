package ru.otus.lyamin.app.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.lyamin.app.dao.impl.AuthorDaoJdbc;
import ru.otus.lyamin.app.entity.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.otus.lyamin.app.prototype.AuthorPrototype.*;

@DisplayName("Класс AuthorDaoJdbcTest должен ")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {
    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @DisplayName("корректно возвращать количество авторов ")
    @Test
    void shouldReturnCorrectCountOfAuthors() {
        int expectedCount = getAuthors().size();
        int actualCount = authorDaoJdbc.countOfAuthors();
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @DisplayName("возвращать автора по id ")
    @Test
    void shouldReturnAuthorById() {
        Author author = authorDaoJdbc.getAuthorById(getAuthor().getId());
        Assertions.assertThat(author).isEqualTo(getAuthor());
    }

    @DisplayName("возвращать всех авторов ")
    @Test
    void shouldReturnAllAuthors() {
        List<Author> actualAuthorList = authorDaoJdbc.getAuthors();
        Assertions.assertThat(actualAuthorList).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(getAuthors());
    }

    @DisplayName("корректно добавлять автора ")
    @Test
    void shouldCorrectlyAddAuthor() {
        Author author = new Author(null, "testAuthorName", "testAuthorLastName");
        long id = authorDaoJdbc.addAuthor(author);
        Author actualAuthor = authorDaoJdbc.getAuthorById(id);
        assertThat(actualAuthor).isNotNull()
                .isInstanceOf(Author.class)
                .extracting("firstName", "lastName")
                .doesNotContainNull()
                .containsExactly("testAuthorName", "testAuthorLastName");

    }

    @DisplayName("корректно обновлять автора ")
    @Test
    void shouldCorrectlyUpdateAuthor() {
        Author expectedAuthor = getAuthor();
        expectedAuthor.setFirstName("testNewName");
        authorDaoJdbc.updateAuthor(expectedAuthor);
        Author actualAuthor = authorDaoJdbc.getAuthorById(getAuthor().getId());
        Assertions.assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("корректно удалять автора ")
    @Test
    void shouldDeleteAuthorById() {
        assertThatCode(() -> authorDaoJdbc.getAuthorById(getDeletableAuthor().getId())).doesNotThrowAnyException();
        int countBeforeDelete = authorDaoJdbc.getAuthors().size();
        authorDaoJdbc.deleteAuthorById(getDeletableAuthor().getId());
        int countAfterDelete = authorDaoJdbc.countOfAuthors();
        assertThat(countBeforeDelete).isGreaterThan(countAfterDelete);
        assertThatThrownBy(() -> authorDaoJdbc.getAuthorById(getDeletableAuthor().getId()));
    }
}