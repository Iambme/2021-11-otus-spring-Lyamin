package ru.otus.lyamin.app.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lyamin.app.dao.AuthorRepository;
import ru.otus.lyamin.app.entity.Author;
import ru.otus.lyamin.app.service.impl.AuthorServiceImpl;
import ru.otus.lyamin.app.service.interf.AuthorService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static ru.otus.lyamin.app.prototype.AuthorPrototype.getAuthor;
import static ru.otus.lyamin.app.prototype.AuthorPrototype.getAuthors;

@DisplayName("Класс AuthorServiceImpl должен ")
@SpringBootTest(classes = AuthorServiceImpl.class)
class AuthorServiceImplTest {
    @MockBean
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorService authorService;

    @DisplayName("возвращать автора по id ")
    @Test
    void shouldReturnAuthorById() {
        Author expectedAuthor = getAuthor();
        when(authorRepository.findById(expectedAuthor.getId())).thenReturn(Optional.of(expectedAuthor));
        Author actualAuthor = authorService.getAuthorById(expectedAuthor.getId());
        assertThat(actualAuthor).isNotNull()
                .isInstanceOf(Author.class)
                .usingRecursiveComparison()
                .isEqualTo(expectedAuthor);
        verify(authorRepository, times(1)).findById(expectedAuthor.getId());
    }

    @DisplayName("возвращать автора по имени ")
    @Test
    void shouldReturnAuthorByName() {
        Author expectedAuthor = getAuthor();
        when(authorRepository.findAuthorByName(expectedAuthor.getName())).thenReturn(Optional.of(expectedAuthor));
        Author actualAuthor = authorService.getAuthorByName(expectedAuthor.getName());
        assertThat(actualAuthor).isNotNull()
                .isInstanceOf(Author.class)
                .usingRecursiveComparison()
                .isEqualTo(expectedAuthor);
        verify(authorRepository, times(1)).findAuthorByName(expectedAuthor.getName());
    }

    @DisplayName("возвращать всех авторов ")
    @Test
    void shouldReturnAllAuthors() {
        List<Author> expectedAuthors = getAuthors();
        when(authorRepository.findAll()).thenReturn(expectedAuthors);
        List<Author> actualAuthors = authorService.getAuthors();
        assertThat(actualAuthors).isNotEmpty()
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(expectedAuthors);
        verify(authorRepository, times(1)).findAll();
    }

    @DisplayName("корректно добавлять автора ")
    @Test
    void shouldCorrectlyAddAuthor() {

        when(authorRepository.save(any(Author.class))).thenReturn(getAuthor());
        Author actualAuthor = authorService.saveAuthor(getAuthor().getName());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(getAuthor());
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @DisplayName("корректно обновлять автора ")
    @Test
    void shouldCorrectlyUpdateAuthor() {
        int expectedCount = 1;
        when(authorRepository.updateNameById(getAuthor().getId(), getAuthor().getName())).thenReturn(expectedCount);
        int actualCount = authorService.updateAuthorNameById(getAuthor().getId(), getAuthor().getName());
        assertThat(actualCount).isEqualTo(expectedCount);
        verify(authorRepository, times(1)).updateNameById(getAuthor().getId(), getAuthor().getName());
    }

    @DisplayName("корректно удалять автора ")
    @Test
    void shouldDeleteAuthorById() {
        authorService.deleteAuthorById(getAuthor().getId());
        verify(authorRepository, times(1)).deleteById(getAuthor().getId());
    }
}