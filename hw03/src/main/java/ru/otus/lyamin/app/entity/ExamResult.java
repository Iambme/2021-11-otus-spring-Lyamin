package ru.otus.lyamin.app.entity;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class ExamResult {
    private User user;
    private boolean isPassed;
    private int correctAnswers;
    private Exam exam;

    public ExamResult(User user, Exam exam) {
        this.user = user;
        this.exam = exam;
    }

    public void applyAnswer(boolean isCorrectAnswerResult) {
        if (isCorrectAnswerResult) {
            correctAnswers++;
        }
    }
}
