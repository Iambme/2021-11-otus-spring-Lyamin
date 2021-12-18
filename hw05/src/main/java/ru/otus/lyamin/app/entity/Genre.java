package ru.otus.lyamin.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    public Genre(String name) {
        this(null, name);
    }

    private Long id;
    private String name;
}
