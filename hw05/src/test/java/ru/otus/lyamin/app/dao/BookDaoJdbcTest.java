package ru.otus.lyamin.app.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.lyamin.app.dao.impl.BookDaoJdbc;
import ru.otus.lyamin.app.entity.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.otus.lyamin.app.prototype.AuthorPrototype.getAuthor;
import static ru.otus.lyamin.app.prototype.BookPrototype.*;
import static ru.otus.lyamin.app.prototype.GenrePrototype.getGenre;

@DisplayName("Класс BookDaoJdbc должен ")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {
    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @DisplayName("корректно возвращать количество книг ")
    @Test
    void shouldReturnCorrectCountOfBook() {
        int expectedCount = getBooks().size();
        int actualCount = bookDaoJdbc.countOfBooks();
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @DisplayName("возвращать книгу по id ")
    @Test
    void shouldReturnBookById() {
        Book book = bookDaoJdbc.getBookById(getBook().getId());
        Assertions.assertThat(book).isEqualTo(getBook());
    }

    @DisplayName("возвращать все книги ")
    @Test
    void shouldReturnAllBook() {
        List<Book> actualBooksList = bookDaoJdbc.getBooks();
        Assertions.assertThat(actualBooksList).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(getBooks());
    }

    @DisplayName("корректно добавлять книгу ")
    @Test
    void shouldCorrectlyAddBook() {
        Book book = new Book(null, "testBookTitle", getAuthor(), getGenre());
        long id = bookDaoJdbc.addBook(book);
        Book actualBook = bookDaoJdbc.getBookById(id);
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
        expectedBook.setTitle("testNewName");
        bookDaoJdbc.updateBook(expectedBook);
        Book actualBook = bookDaoJdbc.getBookById(getBook().getId());
        Assertions.assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("корректно удалять книгу ")
    @Test
    void shouldDeleteBookById() {
        assertThatCode(() -> bookDaoJdbc.getBookById(getAnotherBook().getId())).doesNotThrowAnyException();
        int countBeforeDelete = bookDaoJdbc.getBooks().size();
        bookDaoJdbc.deleteBookById(getAnotherBook().getId());
        int countAfterDelete = bookDaoJdbc.countOfBooks();
        assertThat(countBeforeDelete).isGreaterThan(countAfterDelete);
        assertThatThrownBy(() -> bookDaoJdbc.getBookById(getAnotherBook().getId()));
    }
}