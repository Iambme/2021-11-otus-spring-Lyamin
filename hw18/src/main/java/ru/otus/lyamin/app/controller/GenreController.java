package ru.otus.lyamin.app.controller;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.lyamin.app.dto.GenreDto;
import ru.otus.lyamin.app.service.impl.ResilienceService;
import ru.otus.lyamin.app.service.interf.GenreService;

import java.util.List;

import static ru.otus.lyamin.app.service.impl.ResilienceService.EXCEPTIONS;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;
    private final CircuitBreaker genreCircuitBreaker;
    private final ResilienceService resilienceService;

    @GetMapping("api/genre")
    public List<GenreDto> getAll() {
        return GenreDto.toDtoList(Decorators.ofSupplier(genreService::findAll)
                .withCircuitBreaker(genreCircuitBreaker)
                .withFallback(EXCEPTIONS, resilienceService::genresFallback)
                .decorate().get());
    }
}
