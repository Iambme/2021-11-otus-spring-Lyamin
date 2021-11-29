package ru.otus.lyamin.app.entity;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ExamResult {
    private boolean isPassed;
    private int correctAnswers;

    public void incrementCorrectAnswers(){
        correctAnswers++;
    }
}
