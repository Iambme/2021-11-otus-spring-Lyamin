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
import reactor.core.publisher.Mono;
import ru.otus.lyamin.app.dao.AuthorRepository;
import ru.otus.lyamin.app.dao.BookRepository;
import ru.otus.lyamin.app.dao.GenreRepository;
import ru.otus.lyamin.app.dto.BookDto;
import ru.otus.lyamin.app.entity.Book;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static ru.otus.lyamin.app.prototype.AuthorPrototype.getAuthor;
import static ru.otus.lyamin.app.prototype.BookPrototype.getBook;
import static ru.otus.lyamin.app.prototype.BookPrototype.getBooks;
import static ru.otus.lyamin.app.prototype.GenrePrototype.getGenre;


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
        val bookList = getBooks();

        val json = new Gson().toJson(bookList.stream().map(BookDto::toDto).collect(Collectors.toList()));
        when(bookRepository.findAll()).thenReturn(Flux.fromIterable(bookList));

        client.get().uri("/api/book")
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(json);
    }

    @DisplayName("возвращать книгу")
    @Test
    void shouldCorrectGetById() {
        val book = getBook();

        val json = new Gson().toJson(BookDto.toDto(book));
        when(bookRepository.findById(book.getId())).thenReturn(Mono.just(book));

        client.get().uri("/api/book/" + book.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(json);
    }

    @DisplayName("создавать книгу")
    @Test
    void shouldCorrectCreate() {
        val author = getAuthor();
        val genre = getGenre();
        val book = getBook();

        val json = new Gson().toJson(BookDto.toDto(book));

        when(authorRepository.findById(author.getId())).thenReturn(Mono.just(author));
        when(genreRepository.findById(genre.getId())).thenReturn(Mono.just(genre));
        when(bookRepository.save(any(Book.class))).thenReturn(Mono.just(book));

        client.post().uri("/api/book/")
                .bodyValue(BookDto.toDto(book))
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(json);
    }

    @DisplayName("обновлять книгу")
    @Test
    void shouldCorrectUpdate() {
        val author = getAuthor();
        val genre = getGenre();
        val book = new Book("id_book1", "book_update", author, genre);

        val json = new Gson().toJson(BookDto.toDto(book));

        when(authorRepository.findById(author.getId())).thenReturn(Mono.just(author));
        when(genreRepository.findById(genre.getId())).thenReturn(Mono.just(genre));
        when(bookRepository.findById(book.getId())).thenReturn(Mono.just(book));
        when(bookRepository.save(any(Book.class))).thenReturn(Mono.just(book));

        client.put().uri("/api/book/" + book.getId())
                .bodyValue(BookDto.toDto(book))
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(json);
    }

    @DisplayName("удалять книгу")
    @Test
    void shouldCorrectDelete() {
        val deleteBookId = getBook().getId();

        when(bookRepository.deleteById(deleteBookId)).thenReturn(Mono.empty());

        client.delete().uri("/api/book/" + deleteBookId)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Mono.class);
    }
}