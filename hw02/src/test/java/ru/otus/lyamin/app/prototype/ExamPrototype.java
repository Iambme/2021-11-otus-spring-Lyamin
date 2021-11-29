package ru.otus.lyamin.app.prototype;

import lombok.experimental.UtilityClass;
import ru.otus.lyamin.app.entity.Exam;
import ru.otus.lyamin.app.entity.ExamResult;

import java.util.List;

import static ru.otus.lyamin.app.prototype.QuestionPrototype.getQuestion;
import static ru.otus.lyamin.app.prototype.UserPrototype.getTestUser;

@UtilityClass
public class ExamPrototype {
    public static Exam getPassedExam(){
        return Exam.builder()
                .user(getTestUser())
                .examResult(ExamResult.builder()
                        .isPassed(true)
                        .correctAnswers(1)
                        .build())
                .questionList(List.of(getQuestion(),getQuestion()))
                .successScore(1)
                .build();

    }
    public static Exam getNotPassedExam(){
        return Exam.builder()
                .user(getTestUser())
                .examResult(ExamResult.builder()
                        .isPassed(false)
                        .correctAnswers(0)
                        .build())
                .questionList(List.of(getQuestion(),getQuestion()))
                .successScore(1)
                .build();

    }
}
