package ru.otus.lyamin.app.shell;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.lyamin.app.service.interf.GenreService;

@ShellComponent
public class GenreShellController {
    private final GenreService genreService;
    private final ObjectMapper objectMapper;

    public GenreShellController(GenreService genreService) {
        this.genreService = genreService;
        this.objectMapper = new ObjectMapper();
    }

    @ShellMethod(value = "Get all Genres", key = {"allg", "all-genre"})
    public String getGenres() throws JsonProcessingException {
        return objectMapper.writeValueAsString(genreService.getGenres());
    }

    @ShellMethod(value = "Get Genre by id", key = {"getg", "id-genre"})
    public String getGenreById(@ShellOption Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(genreService.getGenreById(id));
    }

    @ShellMethod(value = "Get Genre by name", key = {"getgn", "name-genre"})
    public String getGenreByName(@ShellOption String name) throws JsonProcessingException {
        return objectMapper.writeValueAsString(genreService.getGenreByName(name));
    }

    @ShellMethod(value = "Insert Genre", key = {"addg", "add-genre"})
    public String addGenre(@ShellOption String name) throws JsonProcessingException {
        return objectMapper.writeValueAsString(genreService.saveGenre(name));

    }

    @ShellMethod(value = "Update Genre", key = {"updg", "update-genre"})
    public String updateGenre(@ShellOption Long id, @ShellOption String name) throws JsonProcessingException {
        int result = genreService.updateGenreNameById(id, name);

        return result == 1 ? "Comment updated successfully" : "Comment has not been updated";
    }

    @ShellMethod(value = "Delete Genre by id", key = {"delg", "delete-genre"})
    public void deleteGenreById(@ShellOption Long id) {
        genreService.deleteGenreById(id);
    }
}
