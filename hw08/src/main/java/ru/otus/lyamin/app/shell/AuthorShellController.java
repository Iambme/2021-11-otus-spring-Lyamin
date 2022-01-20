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
        return objectMapper.writeValueAsString(authorService.findAll());
    }

    @ShellMethod(value = "Get Author by id", key = {"geta", "get-author"})
    public String getAuthorById(@ShellOption String id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(authorService.findById(id));
    }

    @ShellMethod(value = "Get Author by name", key = {"getan", "get-author-by-name"})
    public String getAuthorByName(@ShellOption String name) throws JsonProcessingException {
        return objectMapper.writeValueAsString(authorService.findByName(name));
    }

    @ShellMethod(value = "Insert Author", key = {"adda", "add-author"})
    public String addAuthor(@ShellOption String name) throws JsonProcessingException {
        return objectMapper.writeValueAsString(authorService.save(name));
    }

    @ShellMethod(value = "Update Author", key = {"upda", "update-author"})
    public void updateAuthor(@ShellOption String id, @ShellOption String name) throws JsonProcessingException {
        authorService.updateNameById(id, name);
    }

    @ShellMethod(value = "Delete Author by id", key = {"dela", "delete-author"})
    public void deleteAuthorById(@ShellOption String id) {
        authorService.deleteById(id);
    }

}
