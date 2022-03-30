package ru.otus.lyamin.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

import static javax.persistence.CascadeType.PERSIST;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedEntityGraph(name = "book-graph", attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre")})
@Table(name = "book")
@SecondaryTable(name = "mongo_book", pkJoinColumns = @PrimaryKeyJoinColumn(name = "book_id"))
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Fetch(FetchMode.SELECT)
    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = PERSIST)
    @JoinColumn(name = "author_id")
    private Author author;

    @Fetch(FetchMode.SELECT)
    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = PERSIST)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Column(name = "mongo_id", table = "mongo_book")
    private String mongoId;
}
