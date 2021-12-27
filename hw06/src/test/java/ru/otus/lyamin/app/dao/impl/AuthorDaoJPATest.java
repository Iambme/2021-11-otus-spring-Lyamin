package ru.otus.lyamin.app.dao.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.lyamin.app.entity.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.otus.lyamin.app.prototype.AuthorPrototype.*;

@DisplayName("Класс AuthorDaoJPATest должен ")
@DataJpaTest
@Import(AuthorDaoJPA.class)
class AuthorDaoJPATest {
    @Autowired
    private AuthorDaoJPA authorDaoJPA;
    @Autowired
    private TestEntityManager em;


    @DisplayName("возвращать автора по id ")
    @Test
    void shouldReturnAuthorById() {
        Optional<Author> actualAuthor = authorDaoJPA.getAuthorById(getAuthor().getId());
        Author expectedAuthor = em.find(Author.class, getAuthor().getId());
        Assertions.assertThat(actualAuthor).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать автора по имени ")
    @Test
    void shouldReturnAuthorByName() {
        Optional<Author> actualAuthor = authorDaoJPA.getAuthorByName(getAuthor().getName());
        Author expectedAuthor = em.find(Author.class, getAuthor().getId());
        Assertions.assertThat(actualAuthor).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать всех авторов ")
    @Test
    void shouldReturnAllAuthors() {
        List<Author> actualAuthorList = authorDaoJPA.getAuthors();
        Assertions.assertThat(actualAuthorList).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(getAuthors());
    }

    @DisplayName("корректно добавлять автора ")
    @Test
    void shouldCorrectlyAddAuthor() {
        Author author = new Author(null, "testAuthorName");
        Author actualAuthor = authorDaoJPA.addAuthor(author);
        assertThat(actualAuthor).isNotNull()
                .isInstanceOf(Author.class)
                .hasFieldOrPropertyWithValue("name", "testAuthorName");

    }

    @DisplayName("корректно обновлять автора ")
    @Test
    void shouldCorrectlyUpdateAuthor() {
        Author expectedAuthor = getAuthor();
        expectedAuthor.setName("testNewName");
        authorDaoJPA.updateAuthorNameById(getAuthor().getId(), "testNewName");
        Author actualAuthor = em.find(Author.class, getAuthor().getId());
        Assertions.assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("корректно удалять автора ")
    @Test
    void shouldDeleteAuthorById() {
        Author author = em.find(Author.class, getDeletableAuthor().getId());
        Assertions.assertThat(author).isNotNull();
        em.detach(author);
        authorDaoJPA.deleteAuthorById(getDeletableAuthor().getId());
        author = em.find(Author.class, getDeletableAuthor().getId());
        Assertions.assertThat(author).isNull();
    }
}