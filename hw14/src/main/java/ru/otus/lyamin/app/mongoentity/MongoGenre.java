package ru.otus.lyamin.app.mongoentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoGenre {
    private String id;

    private String name;
}