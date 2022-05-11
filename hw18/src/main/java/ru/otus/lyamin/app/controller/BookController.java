package ru.otus.lyamin.app.controller;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.lyamin.app.dto.BookDto;
import ru.otus.lyamin.app.service.impl.ResilienceService;
import ru.otus.lyamin.app.service.interf.BookService;

import java.util.List;

import static ru.otus.lyamin.app.service.impl.ResilienceService.EXCEPTIONS;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final CircuitBreaker bookCircuitBreaker;
    private final ResilienceService resilienceService;

    @GetMapping("api/book")
    public List<BookDto> getAll() {
        return BookDto.toDtoList(Decorators.ofSupplier(bookService::findAll)
                .withCircuitBreaker(bookCircuitBreaker)
                .withFallback(EXCEPTIONS, resilienceService::booksFallback)
                .decorate().get());
    }

    @GetMapping("api/book/{id}")
    public BookDto getById(@PathVariable long id) {
        return BookDto.toDto(Decorators.ofSupplier(() -> bookService.findById(id))
                .withCircuitBreaker(bookCircuitBreaker)
                .withFallback(EXCEPTIONS, resilienceService::bookFallback)
                .decorate().get());
    }

    @PostMapping("api/book")
    public BookDto create(@RequestBody BookDto bookDto) {
        return BookDto.toDto(bookService.save(BookDto.toEntity(bookDto)));
    }

    @PutMapping("api/book/{id}")
    public BookDto update(@PathVariable long id, @RequestBody BookDto bookDto) {
        bookDto.setId(id);
        return BookDto.toDto(bookService.save(BookDto.toEntity(bookDto)));
    }

    @DeleteMapping("api/book/{id}")
    public void delete(@PathVariable long id) {
        bookService.deleteById(id);
    }
}
