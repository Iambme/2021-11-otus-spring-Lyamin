package ru.otus.lyamin.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Answer {
    private final String answerText;
    private boolean isCorrect;


}
