package ru.otus.lyamin.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.lyamin.app.dao.GenreRepository;
import ru.otus.lyamin.app.dto.GenreDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreRepository genreRepository;

    @GetMapping("api/genre")
    public Flux<GenreDto> getAll() {
        return genreRepository.findAll().map(GenreDto::toDto);
    }
}
