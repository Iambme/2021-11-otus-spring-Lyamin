package ru.otus.lyamin.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 5)
    private Book book;

    public Comment(String text, Long bookId) {
        this.text = text;
        this.book = new Book(bookId, null, null, null);
    }

}