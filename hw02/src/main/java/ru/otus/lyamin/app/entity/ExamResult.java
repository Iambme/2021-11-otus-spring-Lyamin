package ru.otus.lyamin.app.entity;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ExamResult {
    private User user;
    private boolean isPassed;
    private int correctAnswers;
    private Exam exam;

    public void applyAnswer(boolean isCorrectAnswerResult) {
        if (isCorrectAnswerResult) {
            correctAnswers++;
        }
    }
}
