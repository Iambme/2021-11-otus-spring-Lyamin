package ru.otus.lyamin.app.prototype;

import lombok.experimental.UtilityClass;
import ru.otus.lyamin.app.entity.Exam;
import ru.otus.lyamin.app.entity.User;

import java.util.List;

import static ru.otus.lyamin.app.prototype.QuestionPrototype.getQuestion;
import static ru.otus.lyamin.app.prototype.UserPrototype.getTestUser;

@UtilityClass
public class ExamPrototype {
    public static Exam getPassedExam(){
        return Exam.builder()
                .user(getTestUser())
                .questionList(List.of(getQuestion(),getQuestion()))
                .successScore(1)
                .correctAnswers(1)
                .isPassed(true)
                .build();

    }
    public static Exam getNotPassedExam(){
        return Exam.builder()
                .user(new User("test","test"))
                .questionList(List.of(getQuestion(),getQuestion()))
                .successScore(1)
                .correctAnswers(0)
                .isPassed(false)
                .build();

    }
}
