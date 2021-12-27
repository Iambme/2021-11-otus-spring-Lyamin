package ru.otus.lyamin.app.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lyamin.app.dao.interf.BookDao;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.service.interf.BookService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static ru.otus.lyamin.app.prototype.BookPrototype.getBook;
import static ru.otus.lyamin.app.prototype.BookPrototype.getBooks;

@DisplayName("Класс BookServiceImpl должен")
@SpringBootTest(classes = BookServiceImpl.class)
class BookServiceImplTest {
    @MockBean
    private BookDao bookDao;
    @Autowired
    private BookService bookService;

    @DisplayName("возвращать книгу по id ")
    @Test
    void shouldReturnBookById() {
        Book expectedBook = getBook();
        when(bookDao.getBookById(expectedBook.getId())).thenReturn(Optional.of(expectedBook));
        Book actualBook = bookService.getBookById(expectedBook.getId());
        assertThat(actualBook).isNotNull()
                .isInstanceOf(Book.class)
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
        verify(bookDao, times(1)).getBookById(expectedBook.getId());
    }

    @DisplayName("возвращать книгу по названию ")
    @Test
    void shouldReturnBookByTitle() {
        Book expectedBook = getBook();
        when(bookDao.getBookByTitle(expectedBook.getTitle())).thenReturn(Optional.of(expectedBook));
        Book actualBook = bookService.getBookByTitle(expectedBook.getTitle());
        assertThat(actualBook).isNotNull()
                .isInstanceOf(Book.class)
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
        verify(bookDao, times(1)).getBookByTitle(expectedBook.getTitle());
    }

    @DisplayName("возвращать всех книг ")
    @Test
    void shouldReturnAllBooks() {
        List<Book> expectedBooks = getBooks();
        when(bookDao.getBooks()).thenReturn(expectedBooks);
        List<Book> actualBooks = bookService.getBooks();
        assertThat(actualBooks).isNotEmpty()
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(expectedBooks);
        verify(bookDao, times(1)).getBooks();
    }

    @DisplayName("корректно добавлять книгу ")
    @Test
    void shouldCorrectlyAddBook() {

        when(bookDao.addBook(any(Book.class))).thenReturn(getBook());
        Book actualBook = bookService.addBook(getBook().getTitle(),
                getBook().getAuthor().getId(), getBook().getGenre().getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(getBook());
        verify(bookDao, times(1)).addBook(any(Book.class));
    }

    @DisplayName("корректно обновлять книгу ")
    @Test
    void shouldCorrectlyUpdateBook() {
        int expectedCount = 1;
        when(bookDao.updateBookById(getBook().getId(), getBook().getTitle(),
                getBook().getAuthor().getId(), getBook().
                        getGenre().getId())).thenReturn(expectedCount);
        int actualCount = bookService.updateBookById(getBook().getId(), getBook().getTitle(),
                getBook().getAuthor().getId(), getBook().getGenre().getId());
        assertThat(actualCount).isEqualTo(expectedCount);
        verify(bookDao, times(1)).updateBookById(getBook().getId(), getBook().getTitle(),
                getBook().getAuthor().getId(), getBook().
                        getGenre().getId());
    }

    @DisplayName("корректно удалять книгу ")
    @Test
    void shouldDeleteBookById() {
        int expectedCount = 1;
        when(bookDao.deleteBookById(getBook().getId())).thenReturn(expectedCount);
        int actualCount = bookService.deleteBookById(getBook().getId());
        assertThat(actualCount).isEqualTo(expectedCount);
        verify(bookDao, times(1)).deleteBookById(getBook().getId());
    }
}