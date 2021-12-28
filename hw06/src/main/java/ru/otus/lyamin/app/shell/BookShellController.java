package ru.otus.lyamin.app.shell;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.lyamin.app.service.interf.BookService;

@ShellComponent
public class BookShellController {
    private final BookService bookService;
    private final ObjectMapper objectMapper;

    public BookShellController(BookService bookService) {
        this.bookService = bookService;
        this.objectMapper = new ObjectMapper();
    }

    @ShellMethod(value = "Get all Books", key = {"allb", "all-book"})
    public String getBooks() throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookService.getBooks());
    }

    @ShellMethod(value = "Get Book by id", key = {"getb", "id-book"})
    public String getBookById(@ShellOption Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookService.getBookById(id));
    }
    @ShellMethod(value = "Get Book by title", key = {"getbt", "title-book"})
    public String getBookByName(@ShellOption String title) throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookService.getBookByTitle(title));
    }

    @ShellMethod(value = "Insert Book", key = {"addb", "add-book"})
    public String addBook(@ShellOption String name, @ShellOption Long authorId, @ShellOption Long genreId) throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookService.saveBook(name, authorId, genreId));

    }

    @ShellMethod(value = "Update Book", key = {"updb", "update-book"})
    public String updateBook(@ShellOption Long id, @ShellOption String title,
                             @ShellOption Long authorId, @ShellOption Long genreId) throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookService.updateBookById(id, title, authorId, genreId));

    }

    @ShellMethod(value = "Delete Book by id", key = {"delb", "delete-b"})
    public String deleteBookById(@ShellOption Long id) {
        int result = bookService.deleteBookById(id);

        return result == 1 ? "Book deleted successfully" : "Book has not been deleted";
    }
}
