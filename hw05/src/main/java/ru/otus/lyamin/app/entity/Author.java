package ru.otus.lyamin.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private Long id;
    private String firstName;
    private String lastName;

    public Author(String firstName, String lastName) {
        this(null, firstName, lastName);
    }

}
