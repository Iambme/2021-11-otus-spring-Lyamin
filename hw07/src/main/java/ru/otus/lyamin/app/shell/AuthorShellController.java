package ru.otus.lyamin.app.shell;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.lyamin.app.service.interf.AuthorService;

@ShellComponent
public class AuthorShellController {
    private final AuthorService authorService;
    private final ObjectMapper objectMapper;

    public AuthorShellController(AuthorService authorService) {
        this.authorService = authorService;
        this.objectMapper = new ObjectMapper();
    }

    @ShellMethod(value = "Get all Authors", key = {"alla", "all-authors"})
    public String getAuthors() throws JsonProcessingException {
        return objectMapper.writeValueAsString(authorService.getAuthors());
    }

    @ShellMethod(value = "Get Author by id", key = {"geta", "get-author"})
    public String getAuthorById(@ShellOption Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(authorService.getAuthorById(id));
    }

    @ShellMethod(value = "Get Author by name", key = {"getan", "get-author-by-name"})
    public String getAuthorByName(@ShellOption String name) throws JsonProcessingException {
        return objectMapper.writeValueAsString(authorService.getAuthorByName(name));
    }

    @ShellMethod(value = "Insert Author", key = {"adda", "add-author"})
    public String addAuthor(@ShellOption String name) throws JsonProcessingException {
        return objectMapper.writeValueAsString(authorService.saveAuthor(name));
    }
    @ShellMethod(value = "Update Author", key = {"upda", "update-author"})
    public String updateAuthor(@ShellOption Long id, @ShellOption String name) throws JsonProcessingException {
        int result = authorService.updateAuthorNameById(id, name);

        return result == 1 ? "Author updated successfully" : "Author has not been updated";
    }

    @ShellMethod(value = "Delete Author by id", key = {"dela", "delete-author"})
    public void deleteAuthorById(@ShellOption Long id) {
        authorService.deleteAuthorById(id);
    }

}
