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
import ru.otus.lyamin.app.dao.AuthorRepository;
import ru.otus.lyamin.app.dto.AuthorDto;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static ru.otus.lyamin.app.prototype.AuthorPrototype.getAuthors;


@SpringBootTest
@DisplayName("Контроллер для работы с авторами должен")
class AuthorControllerTest {

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorController authorController;

    private WebTestClient client;

    @PostConstruct
    private void init() {
        client = WebTestClient.bindToController(authorController).build();
    }

    @DisplayName("возвращать список авторов")
    @Test
    void shouldCorrectGetAll() {
        val authorList = getAuthors();

        val json = new Gson().toJson(authorList.stream().map(AuthorDto::toDto).collect(Collectors.toList()));
        when(authorRepository.findAll()).thenReturn(Flux.fromIterable(authorList));

        client.get().uri("/api/author")
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(json);
    }
}