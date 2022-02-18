package ru.otus.lyamin.app.dao;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;
import ru.otus.lyamin.app.entity.Genre;


import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Репозиторий для работы с жанрами должен")
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @DisplayName("должен загружать список всех жанров")
    @Test
    void shouldCorrectFindAll() {

        val expectedGenre = new Genre("id_genre1", "genre1");

        val genreFlux = genreRepository.findAll();

        StepVerifier.create(genreFlux)
                .assertNext(actualGenre -> assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre))
                .expectNextCount(2)
                .expectComplete()
                .verify();
    }
}