package ru.otus.lyamin.app.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.exception.LibraryException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Класс BookRepository должен ")
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;


    @DisplayName("возвращать книгу по id ")
    @Test
    void shouldReturnBookById() {
        Optional<Book> expectedBook = bookRepository.findAll().stream().findFirst();
        Optional<Book> actualBook = bookRepository.findById(expectedBook.
                orElseThrow(() -> new LibraryException("Books not found")).getId());
        assertThat(actualBook).isPresent().isNotEmpty().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать книгу по названию ")
    @Test
    void shouldReturnBookByName() {
        Optional<Book> expectedBook = bookRepository.findAll().stream().findFirst();
        Optional<Book> actualBook = bookRepository.findBookByTitle(expectedBook.
                orElseThrow(() -> new LibraryException("Books not found")).getTitle());
        assertThat(actualBook).isPresent().isNotEmpty().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать все книги ")
    @Test
    void shouldReturnAllBooks() {
        int EXPECTED_BOOKS_COUNT = 2;
        List<Book> actualBookList = bookRepository.findAll();
        Assertions.assertThat(actualBookList)
                .usingRecursiveFieldByFieldElementComparator()
                .isNotEmpty().hasSize(EXPECTED_BOOKS_COUNT);
    }
}