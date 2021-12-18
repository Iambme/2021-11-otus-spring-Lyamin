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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static ru.otus.lyamin.app.prototype.AuthorPrototype.getAuthor;
import static ru.otus.lyamin.app.prototype.AuthorPrototype.getAuthors;

@DisplayName("Класс BookServiceImpl должен ")
@SpringBootTest(classes = AuthorServiceImpl.class)
class AuthorServiceImplTest {
    @MockBean
    private AuthorDao authorDao;
    @Autowired
    private AuthorService authorService;


    @DisplayName("корректно возвращать количество авторов ")
    @Test
    void shouldReturnCorrectCountOfAuthors() {
        int expectedCount = getAuthors().size();
        when(authorDao.countOfAuthors()).thenReturn(getAuthors().size());
        int actualCount = authorService.countOfAuthors();
        assertThat(actualCount).isEqualTo(expectedCount);
        verify(authorDao, times(1)).countOfAuthors();
    }

    @DisplayName("возвращать автора по id ")
    @Test
    void shouldReturnAuthorById() {
        Author expectedAuthor = getAuthor();
        when(authorDao.getAuthorById(expectedAuthor.getId())).thenReturn(expectedAuthor);
        Author actualAuthor = authorService.getAuthorById(expectedAuthor.getId());
        assertThat(actualAuthor).isNotNull()
                .isInstanceOf(Author.class)
                .usingRecursiveComparison()
                .isEqualTo(expectedAuthor);
        verify(authorDao, times(1)).getAuthorById(expectedAuthor.getId());
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
        Author testAuthor = getAuthor();
        long expectedId = testAuthor.getId();
        testAuthor.setId(null);
        when(authorDao.addAuthor(testAuthor)).thenReturn(expectedId);
        long actualId = authorService.addAuthor(testAuthor.getFirstName(), testAuthor.getLastName());
        assertThat(actualId).isEqualTo(expectedId);
        verify(authorDao, times(1)).addAuthor(testAuthor);
    }

    @DisplayName("корректно обновлять автора ")
    @Test
    void shouldCorrectlyUpdateAuthor() {
        int expectedCount = 1;
        when(authorDao.updateAuthor(getAuthor())).thenReturn(expectedCount);
        int actualCount = authorService.updateAuthor(getAuthor().getId(), getAuthor().getFirstName(), getAuthor().getLastName());
        assertThat(actualCount).isEqualTo(expectedCount);
        verify(authorDao, times(1)).updateAuthor(getAuthor());
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