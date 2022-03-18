package ru.otus.lyamin.app.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.lyamin.app.service.interf.GenreService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.otus.lyamin.app.prototype.GenrePrototype.getGenres;

@DisplayName("Контроллер для работы с жанрами должен")
@WebMvcTest(GenreController.class)
class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDetailsService userDetails;

    @MockBean
    private GenreService genreService;

    @DisplayName("возвращать список жанров")
    @WithMockUser
    @Test
    void shouldCorrectGetAll() throws Exception {

        String json = new Gson().toJson(getGenres());
        given(genreService.findAll()).willReturn(getGenres());

        mockMvc.perform(get("/api/genre"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(json));
    }

    @DisplayName("для неавторизованного пользователя возвращать ответ 302 (REDIRECTION) при получении жанров")
    @Test
    void GetAllNonAuthorized() throws Exception {
        mockMvc.perform(get("/api/genre"))
                .andExpect(status().isFound());
    }
}