package ru.otus.lyamin.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Author {
    @Id
    private String id;
    private String name;


    public Author(String name) {
        this(null,name);
    }
}
