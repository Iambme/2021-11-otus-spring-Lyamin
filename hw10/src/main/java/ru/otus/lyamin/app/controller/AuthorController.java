package ru.otus.lyamin.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.lyamin.app.dto.AuthorDto;
import ru.otus.lyamin.app.service.interf.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("api/author")
    public List<AuthorDto> getAll() {
        return AuthorDto.toDtoList(authorService.findAll());
    }
}
