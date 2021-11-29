package ru.otus.lyamin.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class User {
    private final String name;
    private final String surname;
}

