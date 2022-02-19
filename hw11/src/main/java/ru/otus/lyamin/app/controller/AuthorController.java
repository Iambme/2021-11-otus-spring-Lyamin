package ru.otus.lyamin.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.lyamin.app.dao.AuthorRepository;
import ru.otus.lyamin.app.dto.AuthorDto;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorRepository authorRepository;

    @GetMapping("/api/author")
    public Flux<AuthorDto> getAll() {
        return authorRepository.findAll().map(AuthorDto::toDto);
    }
}
