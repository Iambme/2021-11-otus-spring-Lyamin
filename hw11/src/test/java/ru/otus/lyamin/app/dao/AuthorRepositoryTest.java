package ru.otus.lyamin.app.dao;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;
import ru.otus.lyamin.app.entity.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Репозиторий для работы с авторами должен")
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName("должен загружать список всех авторов")
    @Test
    void shouldCorrectFindAll() {

        val expectedAuthor = new Author("Stephen King");

        val authorFlux = authorRepository.findAll();

        StepVerifier.create(authorFlux)
                .assertNext(actualAuthor -> assertThat(actualAuthor).extracting("name").isEqualTo(expectedAuthor.getName()))
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }
}