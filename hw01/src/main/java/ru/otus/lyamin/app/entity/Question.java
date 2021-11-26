package ru.otus.lyamin.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Question {
    private final String questionText;
    private final List<Answer> answerList;

    @Override
    public String toString() {
        return  questionText + "\n" +
                 answerList + "\n";
    }
}
