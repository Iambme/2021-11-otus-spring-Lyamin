package ru.otus.lyamin.app.controller;

import com.google.gson.Gson;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.otus.lyamin.app.dao.GenreRepository;
import ru.otus.lyamin.app.dto.GenreDto;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static ru.otus.lyamin.app.prototype.GenrePrototype.getGenres;

@SpringBootTest
@DisplayName("Контроллер для работы с жанрами должен")
class GenreControllerTest {



    @MockBean
    private GenreRepository genreRepository;

    @Autowired
    private GenreController genreController;

    private WebTestClient client;

    @PostConstruct
    private void init() {
        client = WebTestClient.bindToController(genreController).build();
    }

    @DisplayName("возвращать список жанров")
    @Test
    void shouldCorrectGetAll() {
        val genreList =getGenres();

        val json = new Gson().toJson(genreList.stream().map(GenreDto::toDto).collect(Collectors.toList()));
        given(genreRepository.findAll()).willReturn(Flux.fromIterable(genreList));

        client.get().uri("/api/genre")
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(json);
    }

}