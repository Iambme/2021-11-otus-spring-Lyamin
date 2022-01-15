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
        return objectMapper.writeValueAsString(bookService.findAll());
    }

    @ShellMethod(value = "Get Book by id", key = {"getb", "id-book"})
    public String getBookById(@ShellOption String id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookService.findById(id));
    }
    @ShellMethod(value = "Get Book by title", key = {"getbt", "title-book"})
    public String getBookByName(@ShellOption String title) throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookService.findByTitle(title));
    }

    @ShellMethod(value = "Insert Book", key = {"addb", "add-book"})
    public String addBook(@ShellOption String name, @ShellOption String authorId, @ShellOption String genreId) throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookService.save(name, authorId, genreId));

    }

    @ShellMethod(value = "Update Book", key = {"updb", "update-book"})
    public String updateBook(@ShellOption String id, @ShellOption String title,
                             @ShellOption String authorId, @ShellOption String genreId) throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookService.updateById(id, title, authorId, genreId));

    }

    @ShellMethod(value = "Delete Book by id", key = {"delb", "delete-b"})
    public void deleteBookById(@ShellOption String id) {
        bookService.deleteById(id);
    }
}
