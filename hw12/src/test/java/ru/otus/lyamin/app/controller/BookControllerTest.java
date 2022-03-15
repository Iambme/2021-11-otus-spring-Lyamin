package ru.otus.lyamin.app.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.lyamin.app.service.interf.AuthorService;
import ru.otus.lyamin.app.service.interf.BookService;
import ru.otus.lyamin.app.service.interf.GenreService;

import javax.annotation.PostConstruct;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
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
    @MockBean(name = "userDetailsService")
    private UserDetailsService userDetailsService;

    @PostConstruct
    void setUp() {
        String username = "test_user";
        String password = "test_password";
        String role = "TEST";
        UserDetails userDetails = User.withUsername(username)
                .password(password)
                .roles(role)
                .build();
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
    }




    @DisplayName("возвращать список книг")
    @Test
    @WithUserDetails(value = "test_user", userDetailsServiceBeanName = "userDetailsService")
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
    @WithUserDetails(value = "test_user", userDetailsServiceBeanName = "userDetailsService")
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
    @WithUserDetails(value = "test_user", userDetailsServiceBeanName = "userDetailsService")
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
    @WithUserDetails(value = "test_user", userDetailsServiceBeanName = "userDetailsService")
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
    @WithUserDetails(value = "test_user", userDetailsServiceBeanName = "userDetailsService")
    void shouldCorrectDelete() throws Exception {
        mockMvc.perform(delete("/api/book/1"))
                .andExpect(status().isOk());
    }
}