package ru.otus.lyamin.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Answer {
    private final String answerText;
    private final boolean isCorrect;
    private int number;

    @Override
    public String toString() {
        return number + " " + answerText + "  |";

    }
}
