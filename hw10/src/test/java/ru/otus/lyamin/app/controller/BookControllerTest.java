package ru.otus.lyamin.app.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.lyamin.app.service.interf.AuthorService;
import ru.otus.lyamin.app.service.interf.BookService;
import ru.otus.lyamin.app.service.interf.GenreService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.otus.lyamin.app.prototype.BookPrototype.getBook;
import static ru.otus.lyamin.app.prototype.BookPrototype.getBooks;

@DisplayName("Контроллер для работы с книгами должен")
@WebMvcTest
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;

    @DisplayName("возвращать список книг")
    @Test
    void shouldCorrectGetAll() throws Exception {

        String json = new Gson().toJson(getBooks());
        given(bookService.findAll()).willReturn(getBooks());

        mockMvc.perform(get("/api/book"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(json));
    }

    @DisplayName("возвращать книгу")
    @Test
    void shouldCorrectGetById() throws Exception {
        String json = new Gson().toJson(getBook());
        given(bookService.findById(1L)).willReturn(getBook());

        mockMvc.perform(get("/api/book/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(json));
    }

    @DisplayName("создавать книгу")
    @Test
    void shouldCorrectSave() throws Exception {

        String json = new Gson().toJson(getBook());
        given(bookService.save(any())).willReturn(getBook());

        mockMvc.perform(post("/api/book")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(json));
    }

    @DisplayName("обновлять книгу")
    @Test
    void shouldCorrectUpdate() throws Exception {

        String json = new Gson().toJson(getBook());
        given(bookService.save(any())).willReturn(getBook());

        mockMvc.perform(put("/api/book/1")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(json));
    }

    @DisplayName("удалять книгу")
    @Test
    void shouldCorrectDelete() throws Exception {
        mockMvc.perform(delete("/api/book/1"))
                .andExpect(status().isOk());
    }
}