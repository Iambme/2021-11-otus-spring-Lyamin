package ru.otus.lyamin.app.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.service.interf.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookShellController {
    private final BookService bookService;


    @ShellMethod(value = "Get all Books", key = {"allb", "all-book"})
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @ShellMethod(value = "Get Book by id", key = {"getb", "id-book"})
    public Book getBookById(@ShellOption Long id) {
        return bookService.getBookById(id);
    }

    @ShellMethod(value = "Insert Book", key = {"addb", "add-book"})
    public String addBook(@ShellOption String name, @ShellOption Long authorId, @ShellOption Long genreId) {
        return bookService.addBook(name, authorId, genreId).getId().toString();

    }

    @ShellMethod(value = "Update Book", key = {"updb", "update-book"})
    public String updateBook(@ShellOption Long id, @ShellOption String title,
                             @ShellOption Long authorId, @ShellOption Long genreId) {
        int result = bookService.updateBookTitleById(id, title, authorId, genreId);

        return result == 1 ? "Book updated successfully" : "Book has not been updated";
    }

    @ShellMethod(value = "Delete Book by id", key = {"delb", "delete-b"})
    public String deleteBookById(@ShellOption Long id) {
        int result = bookService.deleteBookById(id);

        return result == 1 ? "Book deleted successfully" : "Book has not been deleted";
    }
}
