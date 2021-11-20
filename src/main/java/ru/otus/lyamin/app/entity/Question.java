package ru.otus.lyamin.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class Question {
    private final String questionText;
    private final List<Answer> answerList;

}
