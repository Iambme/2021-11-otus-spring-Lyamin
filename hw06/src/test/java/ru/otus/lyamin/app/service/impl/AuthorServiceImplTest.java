package ru.otus.lyamin.app.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lyamin.app.dao.interf.AuthorDao;
import ru.otus.lyamin.app.entity.Author;
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
    private AuthorDao authorDao;
    @Autowired
    private AuthorService authorService;

    @DisplayName("возвращать автора по id ")
    @Test
    void shouldReturnAuthorById() {
        Author expectedAuthor = getAuthor();
        when(authorDao.getAuthorById(expectedAuthor.getId())).thenReturn(Optional.of(expectedAuthor));
        Author actualAuthor = authorService.getAuthorById(expectedAuthor.getId());
        assertThat(actualAuthor).isNotNull()
                .isInstanceOf(Author.class)
                .usingRecursiveComparison()
                .isEqualTo(expectedAuthor);
        verify(authorDao, times(1)).getAuthorById(expectedAuthor.getId());
    }

    @DisplayName("возвращать автора по имени ")
    @Test
    void shouldReturnAuthorByName() {
        Author expectedAuthor = getAuthor();
        when(authorDao.getAuthorByName(expectedAuthor.getName())).thenReturn(Optional.of(expectedAuthor));
        Author actualAuthor = authorService.getAuthorByName(expectedAuthor.getName());
        assertThat(actualAuthor).isNotNull()
                .isInstanceOf(Author.class)
                .usingRecursiveComparison()
                .isEqualTo(expectedAuthor);
        verify(authorDao, times(1)).getAuthorByName(expectedAuthor.getName());
    }

    @DisplayName("возвращать всех авторов ")
    @Test
    void shouldReturnAllAuthors() {
        List<Author> expectedAuthors = getAuthors();
        when(authorDao.getAuthors()).thenReturn(expectedAuthors);
        List<Author> actualAuthors = authorService.getAuthors();
        assertThat(actualAuthors).isNotEmpty()
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(expectedAuthors);
        verify(authorDao, times(1)).getAuthors();
    }

    @DisplayName("корректно добавлять автора ")
    @Test
    void shouldCorrectlyAddAuthor() {

        when(authorDao.saveAuthor(any(Author.class))).thenReturn(getAuthor());
        Author actualAuthor = authorService.saveAuthor(getAuthor().getName());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(getAuthor());
        verify(authorDao, times(1)).saveAuthor(any(Author.class));
    }

    @DisplayName("корректно обновлять автора ")
    @Test
    void shouldCorrectlyUpdateAuthor() {
        int expectedCount = 1;
        when(authorDao.updateAuthorNameById(getAuthor().getId(), getAuthor().getName())).thenReturn(expectedCount);
        int actualCount = authorService.updateAuthorNameById(getAuthor().getId(), getAuthor().getName());
        assertThat(actualCount).isEqualTo(expectedCount);
        verify(authorDao, times(1)).updateAuthorNameById(getAuthor().getId(), getAuthor().getName());
    }

    @DisplayName("корректно удалять автора ")
    @Test
    void shouldDeleteAuthorById() {
        int expectedCount = 1;
        when(authorDao.deleteAuthorById(getAuthor().getId())).thenReturn(expectedCount);
        int actualCount = authorService.deleteAuthorById(getAuthor().getId());
        assertThat(actualCount).isEqualTo(expectedCount);
        verify(authorDao, times(1)).deleteAuthorById(getAuthor().getId());
    }
}