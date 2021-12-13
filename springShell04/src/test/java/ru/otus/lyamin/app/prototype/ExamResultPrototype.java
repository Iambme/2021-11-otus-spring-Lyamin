package ru.otus.lyamin.app.prototype;

import lombok.experimental.UtilityClass;
import ru.otus.lyamin.app.entity.Exam;
import ru.otus.lyamin.app.entity.ExamResult;
import ru.otus.lyamin.app.entity.Question;

import java.util.List;

import static ru.otus.lyamin.app.prototype.QuestionPrototype.getQuestion;
import static ru.otus.lyamin.app.prototype.UserPrototype.getTestUser;

@UtilityClass
public class ExamResultPrototype {
    private final List<Question> questionList = List.of(getQuestion(), getQuestion());

    public static ExamResult getPassedExamResult() {

        return new ExamResult(getTestUser(), true, 1, new Exam(questionList, 1));
    }

    public static ExamResult getNotPassedExamResult() {
        return new ExamResult(getTestUser(), false, 0, new Exam(questionList, 1));
    }
}
