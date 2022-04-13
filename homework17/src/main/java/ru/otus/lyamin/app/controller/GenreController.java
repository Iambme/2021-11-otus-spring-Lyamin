package ru.otus.lyamin.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.lyamin.app.dto.GenreDto;
import ru.otus.lyamin.app.service.interf.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("api/genre")
    public List<GenreDto> getAll() {
        return GenreDto.toDtoList(genreService.findAll());
    }
}
