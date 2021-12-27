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
    public String getGenreByName(@ShellOption Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(genreService.getGenreById(id));
    }

    @ShellMethod(value = "Insert Genre", key = {"addg", "add-genre"})
    public String addGenre(@ShellOption String name) throws JsonProcessingException {
        return objectMapper.writeValueAsString(genreService.addGenre(name));

    }

    @ShellMethod(value = "Update Genre", key = {"updg", "update-genre"})
    public String updateGenre(@ShellOption Long id, @ShellOption String name) {
        int result = genreService.updateGenreNameById(id, name);

        return result == 1 ? "Genre updated successfully" : "Genre has not been updated";
    }

    @ShellMethod(value = "Delete Genre by id", key = {"delg", "delete-genre"})
    public String deleteGenreById(@ShellOption Long id) {
        int result = genreService.deleteGenreById(id);

        return result == 1 ? "Genre deleted successfully" : "Genre has not been updated";
    }
}
