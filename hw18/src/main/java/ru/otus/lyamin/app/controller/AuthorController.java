package ru.otus.lyamin.app.controller;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.lyamin.app.dto.AuthorDto;
import ru.otus.lyamin.app.service.impl.ResilienceService;
import ru.otus.lyamin.app.service.interf.AuthorService;

import java.util.List;

import static ru.otus.lyamin.app.service.impl.ResilienceService.EXCEPTIONS;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final CircuitBreaker authorCircuitBreaker;
    private final ResilienceService resilienceService;

    @GetMapping("api/author")
    public List<AuthorDto> getAll() {
        return AuthorDto.toDtoList(Decorators.ofSupplier(authorService::findAll)
                .withCircuitBreaker(authorCircuitBreaker)
                .withFallback(EXCEPTIONS, resilienceService::authorsFallback)
                .decorate().get());
    }
}
