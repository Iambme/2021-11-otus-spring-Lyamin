package ru.otus.lyamin.app.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lyamin.app.dao.BookRepository;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.service.impl.BookServiceImpl;
import ru.otus.lyamin.app.service.interf.AuthorService;
import ru.otus.lyamin.app.service.interf.BookService;
import ru.otus.lyamin.app.service.interf.GenreService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static ru.otus.lyamin.app.prototype.AuthorPrototype.getAnotherAuthor;
import static ru.otus.lyamin.app.prototype.BookPrototype.getBook;
import static ru.otus.lyamin.app.prototype.BookPrototype.getBooks;
import static ru.otus.lyamin.app.prototype.GenrePrototype.getAnotherGenre;

@DisplayName("Класс BookServiceImpl должен")
@SpringBootTest(classes = BookServiceImpl.class)
class BookServiceImplTest {
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private  AuthorService authorService;
    @MockBean
    private  GenreService genreService;
    @Autowired
    private BookService bookService;

    @DisplayName("возвращать книгу по id ")
    @Test
    void shouldReturnBookById() {
        Book expectedBook = getBook();
        when(bookRepository.findById(expectedBook.getId())).thenReturn(Optional.of(expectedBook));
        Book actualBook = bookService.findById(expectedBook.getId());
        assertThat(actualBook).isNotNull()
                .isInstanceOf(Book.class)
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
        verify(bookRepository, times(1)).findById(expectedBook.getId());
    }

    @DisplayName("возвращать книгу по названию ")
    @Test
    void shouldReturnBookByTitle() {
        Book expectedBook = getBook();
        when(bookRepository.findBookByTitle(expectedBook.getTitle())).thenReturn(Optional.of(expectedBook));
        Book actualBook = bookService.findByTitle(expectedBook.getTitle());
        assertThat(actualBook).isNotNull()
                .isInstanceOf(Book.class)
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
        verify(bookRepository, times(1)).findBookByTitle(expectedBook.getTitle());
    }

    @DisplayName("возвращать всех книг ")
    @Test
    void shouldReturnAllBooks() {
        List<Book> expectedBooks = getBooks();
        when(bookRepository.findAll()).thenReturn(expectedBooks);
        List<Book> actualBooks = bookService.findAll();
        assertThat(actualBooks).isNotEmpty()
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(expectedBooks);
        verify(bookRepository, times(1)).findAll();
    }

    @DisplayName("корректно добавлять книгу ")
    @Test
    void shouldCorrectlyAddBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(getBook());
        Book actualBook = bookService.save(getBook().getTitle(),
                getBook().getAuthor().getId(), getBook().getGenre().getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(getBook());
        verify(bookRepository, times(1)).save(any(Book.class));
        verify(authorService, times(1)).findById(getBook().getAuthor().getId());
        verify(genreService, times(1)).findById(getBook().getGenre().getId());
    }

    @DisplayName("корректно обновлять книгу ")
    @Test
    void shouldCorrectlyUpdateBook() {
        Book expectedBook = getBook();
        expectedBook.setTitle("testNewTitle");
        expectedBook.setAuthor(getAnotherAuthor());
        expectedBook.setGenre(getAnotherGenre());
        when(bookRepository.save(any(Book.class))).thenReturn(expectedBook);
        Book actualBook = bookService.updateById(expectedBook.getId(), expectedBook.getTitle(),
                expectedBook.getAuthor().getId(), expectedBook.getGenre().getId());
        assertThat(actualBook).isEqualTo(expectedBook);
        verify(bookRepository, times(1)).save(any(Book.class));
        verify(authorService, times(1)).findById(expectedBook.getAuthor().getId());
        verify(genreService, times(1)).findById(expectedBook.getGenre().getId());
    }

    @DisplayName("корректно удалять книгу ")
    @Test
    void shouldDeleteBookById() {
        bookService.deleteById(getBook().getId());
        verify(bookRepository, times(1)).deleteById(getBook().getId());
    }
}