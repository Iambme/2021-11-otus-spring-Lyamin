package ru.otus.lyamin.app.dao.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.lyamin.app.dao.QueryCounter;
import ru.otus.lyamin.app.entity.Book;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.otus.lyamin.app.dao.QueryCounter.getExpectedQueriesCount;
import static ru.otus.lyamin.app.prototype.AuthorPrototype.getAnotherAuthor;
import static ru.otus.lyamin.app.prototype.AuthorPrototype.getAuthor;
import static ru.otus.lyamin.app.prototype.BookPrototype.getBook;
import static ru.otus.lyamin.app.prototype.BookPrototype.getBooks;
import static ru.otus.lyamin.app.prototype.GenrePrototype.getAnotherGenre;
import static ru.otus.lyamin.app.prototype.GenrePrototype.getGenre;

@DisplayName("Класс BookDaoJPA должен ")
@DataJpaTest
@Import(BookDaoJPA.class)
class BookDaoJPATest {
    @Autowired
    private BookDaoJPA bookDaoJPA;

    @Autowired
    private TestEntityManager em;
    private QueryCounter queryCounter;

    @BeforeEach
    void setUp() {
        queryCounter = new QueryCounter(em);
    }

    @DisplayName("возвращать книгу по id ")
    @Test
    void shouldReturnBookById() {
        Optional<Book> actualBook = bookDaoJPA.getBookById(getBook().getId());
        Book expectedBook = em.find(Book.class, getBook().getId());
        Assertions.assertThat(actualBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
        assertThat(queryCounter.getQueriesCount()).isEqualTo(getExpectedQueriesCount());
    }

    @DisplayName("возвращать книгу по имени ")
    @Test
    void shouldReturnBookByName() {
        Optional<Book> actualBook = bookDaoJPA.getBookByTitle(getBook().getTitle());
        Book expectedBook = em.find(Book.class, getBook().getId());
        assertThat(actualBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
        assertThat(queryCounter.getQueriesCount()).isEqualTo(getExpectedQueriesCount());
    }

    @DisplayName("возвращать все книги ")
    @Test
    void shouldReturnAllBooks() {
        List<Book> actualBookList = bookDaoJPA.getBooks();
        Assertions.assertThat(actualBookList).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(getBooks());
        assertThat(queryCounter.getQueriesCount()).isEqualTo(getExpectedQueriesCount());
    }

    @DisplayName("корректно добавлять книгу ")
    @Test
    void shouldCorrectlyAddBook() {
        Book book = new Book(null, "testBookTitle", getAuthor(), getGenre());
        Book actualBook = bookDaoJPA.addBook(book);
        assertThat(actualBook).isNotNull()
                .isInstanceOf(Book.class)
                .extracting("title", "author.id", "genre.id")
                .doesNotContainNull()
                .containsExactly("testBookTitle", 1L, 1L);

    }

    @DisplayName("корректно обновлять книгу ")
    @Test
    void shouldCorrectlyUpdateBook() {
        Book expectedBook = getBook();
        expectedBook.setTitle("testNewTitle");
        expectedBook.setAuthor(getAnotherAuthor());
        expectedBook.setGenre(getAnotherGenre());
        bookDaoJPA.updateBookById(getBook().getId(), "testNewTitle", getAnotherAuthor().getId(), getAnotherGenre().getId());
        Optional<Book> actualBook = bookDaoJPA.getBookById(getBook().getId());
        Assertions.assertThat(actualBook).isPresent().get().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("корректно удалять книгу ")
    @Test
    void shouldDeleteBookById() {
        Book Book = em.find(Book.class, getBook().getId());
        Assertions.assertThat(Book).isNotNull();
        em.detach(Book);
        bookDaoJPA.deleteBookById(getBook().getId());
        Book = em.find(Book.class, getBook().getId());
        Assertions.assertThat(Book).isNull();
    }
}