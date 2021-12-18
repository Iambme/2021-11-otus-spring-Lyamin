package ru.otus.lyamin.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    public Book(Long id, String title, long authorId, long genreId) {
        this.id = id;
        this.title = title;
        this.author = new Author(authorId, null, null);
        this.genre = new Genre(genreId, null);
    }

    public Book(String name, long authorId, long genreId) {
        this(null, name, authorId, genreId);
    }

    private Long id;
    private String title;
    private Author author;
    private Genre genre;
}
