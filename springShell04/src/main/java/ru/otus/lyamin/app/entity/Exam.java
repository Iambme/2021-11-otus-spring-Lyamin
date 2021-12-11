package ru.otus.lyamin.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class Exam {
    private final List<Question> questionList;
    private final int successScore;
}
