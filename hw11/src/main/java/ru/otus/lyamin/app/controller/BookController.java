package ru.otus.lyamin.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.lyamin.app.dao.AuthorRepository;
import ru.otus.lyamin.app.dao.BookRepository;
import ru.otus.lyamin.app.dao.CommentRepository;
import ru.otus.lyamin.app.dao.GenreRepository;
import ru.otus.lyamin.app.dto.BookDto;
import ru.otus.lyamin.app.entity.Book;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    @GetMapping("/api/book")
    public Flux<BookDto> getAll() {
        return bookRepository.findAll().map(BookDto::toDto);
    }

    @GetMapping("/api/book/{id}")
    public Mono<BookDto> getById(@PathVariable String id) {
        return bookRepository.findById(id).map(BookDto::toDto);
    }

    @PostMapping("/api/book")
    public Mono<BookDto> create(@RequestBody BookDto bookDto) {
        return Mono.zip(authorRepository.findById(bookDto.getAuthor().getId()),
                        genreRepository.findById(bookDto.getGenre().getId()))
                .flatMap(zip -> {
                    Book book = BookDto.toEntity(bookDto);
                    book.setAuthor(zip.getT1());
                    book.setGenre(zip.getT2());
                    return bookRepository.save(book);
                })
                .map(BookDto::toDto);
    }

    @PutMapping("/api/book/{id}")
    public Mono<BookDto> update(@PathVariable String id, @RequestBody BookDto bookDto) {
        return Mono.zip(authorRepository.findById(bookDto.getAuthor().getId()),
                        genreRepository.findById(bookDto.getGenre().getId()),
                        bookRepository.findById(id))
                .flatMap(zip -> {
                    Book updateBook = BookDto.toEntity(bookDto);
                    updateBook.setAuthor(zip.getT1());
                    updateBook.setGenre(zip.getT2());
                    Book book = zip.getT3();
                    BeanUtils.copyProperties(updateBook, book, "id");
                    return bookRepository.save(book);
                })
                .map(BookDto::toDto);
    }

    @DeleteMapping("/api/book/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return Mono.zip(commentRepository.deleteByBookId(id), bookRepository.deleteById(id))
                .flatMap(result -> Mono.empty());
    }
}

