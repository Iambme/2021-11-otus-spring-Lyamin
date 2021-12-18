package ru.otus.lyamin.app.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.lyamin.app.entity.Genre;
import ru.otus.lyamin.app.service.interf.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GenreShellController {
    private final GenreService genreService;

    @ShellMethod(value = "Get Genres count", key = {"cog", "count-genres"})
    public int countOfGenres() {
        return genreService.countOfGenres();
    }

    @ShellMethod(value = "Get all Genres", key = {"allg", "all-genre"})
    public List<Genre> getGenres() {
        return genreService.getGenres();
    }

    @ShellMethod(value = "Get Genre by id", key = {"getg", "id-genre"})
    public Genre getGenreById(@ShellOption Long id) {
        return genreService.getGenreById(id);
    }

    @ShellMethod(value = "Insert Genre", key = {"addg", "add-genre"})
    public String addGenre(@ShellOption String name) {
        Long id = genreService.addGenre(name);

        return id > 0 ? "Genre added successfully" : "Genre has not been added";
    }

    @ShellMethod(value = "Update Genre", key = {"updg", "update-genre"})
    public String updateGenre(@ShellOption Long id, @ShellOption String name) {
        int result = genreService.updateGenre(id, name);

        return result == 1 ? "Genre updated successfully" : "Genre has not been updated";
    }

    @ShellMethod(value = "Delete Genre by id", key = {"delg", "delete-genre"})
    public String deleteGenreById(@ShellOption Long id) {
        int result = genreService.deleteGenreById(id);

        return result == 1 ? "Genre deleted successfully" : "Genre has not been updated";
    }
}
