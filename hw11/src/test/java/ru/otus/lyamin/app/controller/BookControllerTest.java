package ru.otus.lyamin.app.controller;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.lyamin.app.dao.AuthorRepository;
import ru.otus.lyamin.app.dao.BookRepository;
import ru.otus.lyamin.app.dao.GenreRepository;
import ru.otus.lyamin.app.entity.Author;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.entity.Genre;

import javax.annotation.PostConstruct;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@SpringBootTest
@DisplayName("Контроллер для работы с книгами должен")
class BookControllerTest {


    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookController bookController;

    private WebTestClient client;

    @PostConstruct
    private void init() {
        client = WebTestClient.bindToController(bookController).build();
    }

    @DisplayName("возвращать список книг")
    @Test
    void shouldCorrectGetAll() {
        val bookList = List.of(
                new Book("id_book1", "book1",
                        new Author("id_author1", "author1"),
                        new Genre("id_genre1", "genre1")),
                new Book("id_book2", "book2",
                        new Author("id_author2", "author2"),
                        new Genre("id_genre2", "genre2")),
                new Book("id_book3", "book3",
                        new Author("id_author3", "author3"),
                        new Genre("id_genre3", "genre3"))
        );

        val json = new Gson().toJson(bookList.stream().map(bookMapper::toDto).collect(Collectors.toList()));
        given(bookRepository.findAll()).willReturn(Flux.fromIterable(bookList));

        client.get().uri("/api/book")
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(json);
    }

    @DisplayName("возвращать книгу")
    @Test
    void shouldCorrectGetById() {
        val book = new Book("id_book1", "book1",
                new Author("id_author1", "author1"),
                new Genre("id_genre1", "genre1"));

        val json = new Gson().toJson(bookMapper.toDto(book));
        given(bookRepository.findById(book.getId())).willReturn(Mono.just(book));

        client.get().uri("/api/book/" + book.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(json);
    }

    @DisplayName("создавать книгу")
    @Test
    void shouldCorrectCreate() {
        val author = new Author("id_author1", "author1");
        val genre = new Genre("id_genre1", "genre1");
        val book = new Book("book_new", author, genre);

        val json = new Gson().toJson(bookMapper.toDto(book));

        given(authorRepository.findById(author.getId())).willReturn(Mono.just(author));
        given(genreRepository.findById(genre.getId())).willReturn(Mono.just(genre));
        given(bookRepository.save(any(Book.class))).willReturn(Mono.just(book));

        client.post().uri("/api/book/")
                .bodyValue(bookMapper.toDto(book))
                .exchange()
                .expectStatus().isCreated()
                .expectBody().json(json);
    }

    @DisplayName("обновлять книгу")
    @Test
    void shouldCorrectUpdate() {
        val author = new Author("id_author1", "author1");
        val genre = new Genre("id_genre1", "genre1");
        val book = new Book("id_book1", "book_update", author, genre);

        val json = new Gson().toJson(bookMapper.toDto(book));

        given(authorRepository.findById(author.getId())).willReturn(Mono.just(author));
        given(genreRepository.findById(genre.getId())).willReturn(Mono.just(genre));
        given(bookRepository.findById(book.getId())).willReturn(Mono.just(book));
        given(bookRepository.save(any(Book.class))).willReturn(Mono.just(book));

        client.put().uri("/api/book/" + book.getId())
                .bodyValue(bookMapper.toDto(book))
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(json);
    }

    @DisplayName("удалять книгу")
    @Test
    void shouldCorrectDelete() {
        val deleteBookId = "id_book3";

        given(bookRepository.deleteById(deleteBookId)).willReturn(Mono.empty());

        client.delete().uri("/api/book/" + deleteBookId)
                .exchange()
                .expectStatus().isOk();
    }
}