package ru.otus.lyamin.app.prototype;

import lombok.experimental.UtilityClass;
import ru.otus.lyamin.app.entity.Exam;
import ru.otus.lyamin.app.entity.ExamResult;
import ru.otus.lyamin.app.entity.Question;

import java.util.List;

import static ru.otus.lyamin.app.prototype.QuestionPrototype.getQuestion;
import static ru.otus.lyamin.app.prototype.UserPrototype.getTestUser;

@UtilityClass
public class ExamPrototype {
    private final List<Question> questionList = List.of(getQuestion(), getQuestion());

    public static ExamResult getPassedExamResult() {

        return ExamResult.builder()
                .user(getTestUser())
                .isPassed(true)
                .correctAnswers(1)
                .exam(new Exam(questionList, 1))
                .build();
    }

    public static ExamResult getNotPassedExamResult() {
        return ExamResult.builder()
                .user(getTestUser())
                .isPassed(false)
                .correctAnswers(1)
                .exam(new Exam(questionList, 1))
                .build();

    }
}
