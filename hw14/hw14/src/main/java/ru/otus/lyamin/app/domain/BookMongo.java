package ru.otus.lyamin.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class BookMongo {
    @Id
    private String id;

    private String name;
    private AuthorMongo author;
    private GenreMongo genre;
}