package ru.otus.lyamin.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedEntityGraph(name = "book-graph", attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre")})
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;


    @ManyToOne(targetEntity = Author.class , fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(targetEntity = Genre.class , fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;


    public Book(Long id, String title, long authorId, long genreId) {
        this.id = id;
        this.title = title;
        this.author = new Author(authorId, null);
        this.genre = new Genre(genreId, null);
    }

    public Book(String name, long authorId, long genreId) {
        this(null, name, authorId, genreId);
    }
}
