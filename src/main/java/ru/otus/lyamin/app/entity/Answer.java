package ru.otus.lyamin.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {
    private final String answerText;
    private boolean isCorrect;

    @Override
    public String toString() {
        return  answerText + "  |";

    }
}
