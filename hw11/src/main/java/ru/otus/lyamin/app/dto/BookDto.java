package ru.otus.lyamin.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.lyamin.app.entity.Author;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.entity.Genre;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String id;
    private String title;
    private Author author;
    private Genre genre;


    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre());
    }


    public static List<BookDto> toDtoList(List<Book> entities) {
        return entities.stream().map(BookDto::toDto).collect(Collectors.toList());
    }

    public static Book toEntity(BookDto bookDto) {
        return new Book(bookDto.getId(), bookDto.getTitle(), bookDto.getAuthor(), bookDto.getGenre());
    }


    public static List<Book> toEntityList(List<BookDto> dto) {
        return dto.stream().map(BookDto::toEntity).collect(Collectors.toList());
    }

}
