//package ru.otus.lyamin.app.impl;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import ru.otus.lyamin.app.dao.interf.BookDao;
//import ru.otus.lyamin.app.entity.Book;
//import ru.otus.lyamin.app.service.impl.BookServiceImpl;
//import ru.otus.lyamin.app.service.interf.BookService;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//import static ru.otus.lyamin.app.prototype.BookPrototype.getBook;
//import static ru.otus.lyamin.app.prototype.BookPrototype.getBooks;
//
//@DisplayName("Класс BookServiceImpl должен")
//@SpringBootTest(classes = BookServiceImpl.class)
//class BookServiceImplTest {
//    @MockBean
//    private BookDao bookDao;
//    @Autowired
//    private BookService bookService;
//
//
//    @DisplayName("корректно возвращать количество книг ")
//    @Test
//    void shouldReturnCorrectCountOfBooks() {
//        int expectedCount = getBooks().size();
//        when(bookDao.countOfBooks()).thenReturn(getBooks().size());
//        int actualCount = bookService.countOfBooks();
//        assertThat(actualCount).isEqualTo(expectedCount);
//        verify(bookDao, times(1)).countOfBooks();
//    }
//
//    @DisplayName("возвращать книгу по id ")
//    @Test
//    void shouldReturnBookById() {
//        Book expectedBook = getBook();
//        when(bookDao.getBookById(expectedBook.getId())).thenReturn(expectedBook);
//        Book actualBook = bookService.getBookById(expectedBook.getId());
//        assertThat(actualBook).isNotNull()
//                .isInstanceOf(Book.class)
//                .usingRecursiveComparison()
//                .isEqualTo(expectedBook);
//        verify(bookDao, times(1)).getBookById(expectedBook.getId());
//    }
//
//    @DisplayName("возвращать всех книг ")
//    @Test
//    void shouldReturnAllBooks() {
//        List<Book> expectedBooks = getBooks();
//        when(bookDao.getBooks()).thenReturn(expectedBooks);
//        List<Book> actualBooks = bookService.getBooks();
//        assertThat(actualBooks).isNotEmpty()
//                .usingRecursiveFieldByFieldElementComparator()
//                .isEqualTo(expectedBooks);
//        verify(bookDao, times(1)).getBooks();
//    }
//
//    @DisplayName("корректно добавлять книгу ")
//    @Test
//    void shouldCorrectlyAddBook() {
//        Book testBook = new Book(getBook().getTitle(),
//                getBook().getAuthor().getId(), getBook().getGenre().getId());
//
//        long expectedId = getBook().getId();
//        when(bookDao.addBook(testBook)).thenReturn(expectedId);
//        long actualId = bookService.addBook(testBook.getTitle(),
//                testBook.getAuthor().getId(), testBook.getGenre().getId());
//        assertThat(actualId).isEqualTo(expectedId);
//        verify(bookDao, times(1)).addBook(testBook);
//    }
//
//    @DisplayName("корректно обновлять книгу ")
//    @Test
//    void shouldCorrectlyUpdateBook() {
//        Book testBook = new Book(getBook().getId(), getBook().getTitle(),
//                getBook().getAuthor().getId(), getBook().getGenre().getId());
//
//        int expectedCount = 1;
//        when(bookDao.updateBook(testBook)).thenReturn(expectedCount);
//        int actualCount = bookService.updateBook(getBook().getId(), getBook().getTitle(),
//                getBook().getAuthor().getId(), getBook().getGenre().getId());
//        assertThat(actualCount).isEqualTo(expectedCount);
//        verify(bookDao, times(1)).updateBook(testBook);
//    }
//
//    @DisplayName("корректно удалять книгу ")
//    @Test
//    void shouldDeleteBookById() {
//        int expectedCount = 1;
//        when(bookDao.deleteBookById(getBook().getId())).thenReturn(expectedCount);
//        int actualCount = bookService.deleteBookById(getBook().getId());
//        assertThat(actualCount).isEqualTo(expectedCount);
//        verify(bookDao, times(1)).deleteBookById(getBook().getId());
//    }
//}