package ru.otus.lyamin.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.lyamin.app.dto.BookDto;
import ru.otus.lyamin.app.service.interf.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("api/book")
    public List<BookDto> getAll() {
        return BookDto.toDtoList(bookService.findAll());
    }

    @GetMapping("api/book/{id}")
    public BookDto getById(@PathVariable long id) {
        return BookDto.toDto(bookService.findById(id));
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
