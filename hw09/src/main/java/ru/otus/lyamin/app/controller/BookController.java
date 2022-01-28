package ru.otus.lyamin.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.lyamin.app.dto.BookDto;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.service.interf.AuthorService;
import ru.otus.lyamin.app.service.interf.BookService;
import ru.otus.lyamin.app.service.interf.GenreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("/")
    public String allPage(Model model) {
        List<BookDto> bookDtoList = BookDto.toDtoList(bookService.findAll());
        model.addAttribute("books", bookDtoList);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") String id, Model model) {
        BookDto bookDto = BookDto.toDto(bookService.findById(id));
        model.addAttribute("book", bookDto);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "edit";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        BookDto bookDto = new BookDto();
        model.addAttribute("book", bookDto);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "add";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") String id) {
        bookService.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String updateBook(BookDto bookDto) {
        Book book = BookDto.toEntity(bookDto);
        bookService.save(book);
        return "redirect:/";
    }
}
