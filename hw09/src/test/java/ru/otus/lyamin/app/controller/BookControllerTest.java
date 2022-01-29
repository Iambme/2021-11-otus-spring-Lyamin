package ru.otus.lyamin.app.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.lyamin.app.entity.Author;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.entity.Genre;
import ru.otus.lyamin.app.service.interf.AuthorService;
import ru.otus.lyamin.app.service.interf.BookService;
import ru.otus.lyamin.app.service.interf.GenreService;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Контроллер для работы с книгами должен")
@WebMvcTest
class BookControllerTest {
    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;
    @Autowired
    private MockMvc mockMvc;


    @DisplayName("возвращать страницу с книгами")
    @Test
    void shouldCorrectGetListPage() throws Exception {
        given(bookService.findAll()).willReturn(
                List.of(new Book(1L, "book1",
                        new Author(1L, "author1"),
                        new Genre(1L, "genre1"))));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(content().string(containsString("book1")));
        verify(bookService, times(1)).findAll();

    }

    @DisplayName("возвращать страницу для редактирования книги")
    @Test
    void shouldCorrectGetEditPage() throws Exception {
        given(bookService.findById(1L)).willReturn(
                new Book(1L, "book1",
                        new Author(1L, "author1"),
                        new Genre(1L, "genre1")));
        mockMvc.perform(get("/edit?id=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(content().string(containsString("book1")));
    }

    @DisplayName("возвращать страницу для добавления книги")
    @Test
    void shouldCorrectGetAddPage() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add"))
                .andExpect(content().string(containsString("Add book")));
    }

    @DisplayName("делать редирект при удалении книги")
    @Test
    void shouldCorrectDeleteBook() throws Exception {
        mockMvc.perform(get("/delete?id=1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }


    @DisplayName("делать редирект при создании или редактировании книги")
    @Test
    void updateBook() throws Exception {
        mockMvc.perform(post("/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }
}