package ru.otus.lyamin.app.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.lyamin.app.entity.Author;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED;
import static ru.otus.lyamin.app.prototype.AuthorPrototype.getAuthor;

@DisplayName("Класс AuthorRepository должен ")
@DataJpaTest
@AutoConfigureTestDatabase(connection = H2, replace = AUTO_CONFIGURED)
class AuthorRepositoryTest {
    @Autowired
    private TestEntityManager em;
    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName("возвращать автора по имени ")
    @Test
    void shouldReturnAuthorByName() {
        Optional<Author> actualAuthor = authorRepository.findAuthorByName(getAuthor().getName());
        Author expectedAuthor = em.find(Author.class, getAuthor().getId());
        Assertions.assertThat(actualAuthor).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("корректно обновлять автора ")
    @Test
    void shouldCorrectlyUpdateAuthor() {
        Author expectedAuthor = getAuthor();
        expectedAuthor.setName("testNewName");
        authorRepository.updateNameById(getAuthor().getId(), "testNewName");
        Author actualAuthor = em.find(Author.class, getAuthor().getId());
        Assertions.assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}