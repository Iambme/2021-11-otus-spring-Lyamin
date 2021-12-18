package ru.otus.lyamin.app.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.lyamin.app.entity.Author;
import ru.otus.lyamin.app.service.interf.AuthorService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellController {
    private final AuthorService authorService;

    @ShellMethod(value = "Get Authors count", key = {"coa", "count-authors"})
    public int countOfAuthors() {
        return authorService.countOfAuthors();
    }

    @ShellMethod(value = "Get all Authors", key = {"alla", "all-authors"})
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @ShellMethod(value = "Get Author by id", key = {"geta", "get-author"})
    public Author getAuthorById(@ShellOption Long id) {
        return authorService.getAuthorById(id);
    }

    @ShellMethod(value = "Insert Author", key = {"adda", "add-author"})
    public String addAuthor(@ShellOption String firstName, @ShellOption String lastName) {
        Long id = authorService.addAuthor(firstName, lastName);

        return id > 0 ? "Author added successfully" : "Author has not been added";
    }

    @ShellMethod(value = "Update Author", key = {"upda", "update-author"})
    public String updateAuthor(@ShellOption Long id, @ShellOption String firstName, @ShellOption String lastName) {
        int result = authorService.updateAuthor(id, firstName, lastName);

        return result == 1 ? "Author updated successfully" : "Author has not been updated";
    }

    @ShellMethod(value = "Delete Author by id", key = {"dela", "delete-author"})
    public String deleteAuthorById(@ShellOption Long id) {
        int result = authorService.deleteAuthorById(id);

        return result == 1 ? "Author deleted successfully" : "Author has not been deleted";
    }

}
