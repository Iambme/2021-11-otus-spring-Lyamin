package ru.otus.lyamin.app.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.lyamin.app.entity.Book;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.lyamin.app.dao.QueryCounter.getExpectedQueriesCount;
import static ru.otus.lyamin.app.prototype.BookPrototype.getBook;
import static ru.otus.lyamin.app.prototype.BookPrototype.getBooks;

@DisplayName("Класс BookRepository должен ")
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private TestEntityManager em;
    private QueryCounter queryCounter;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        queryCounter = new QueryCounter(em);
    }

    @DisplayName("возвращать книгу по id ")
    @Test
    void shouldReturnBookById() {
        Optional<Book> actualBook = bookRepository.findById(getBook().getId());
        Book expectedBook = em.find(Book.class, getBook().getId());
        assertThat(actualBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
        assertThat(queryCounter.getQueriesCount()).isEqualTo(getExpectedQueriesCount());
    }

    @DisplayName("возвращать книгу по названию ")
    @Test
    void shouldReturnBookByName() {
        Optional<Book> actualBook = bookRepository.findBookByTitle(getBook().getTitle());
        Book expectedBook = em.find(Book.class, getBook().getId());
        assertThat(actualBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
        assertThat(queryCounter.getQueriesCount()).isEqualTo(getExpectedQueriesCount());
    }

    @DisplayName("возвращать все книги ")
    @Test
    void shouldReturnAllBooks() {
        List<Book> actualBookList = bookRepository.findAll();
        Assertions.assertThat(actualBookList).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(getBooks());
        assertThat(queryCounter.getQueriesCount()).isEqualTo(getExpectedQueriesCount());
    }
}