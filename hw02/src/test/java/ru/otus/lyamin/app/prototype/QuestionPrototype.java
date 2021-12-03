package ru.otus.lyamin.app.prototype;

import lombok.experimental.UtilityClass;
import ru.otus.lyamin.app.entity.Question;

import java.util.List;

import static ru.otus.lyamin.app.prototype.AnswerPrototype.getCorrectAnswer;
import static ru.otus.lyamin.app.prototype.AnswerPrototype.getIncorrectAnswer;

@UtilityClass
public class QuestionPrototype {

public static Question getQuestion(){
    return Question.builder()
            .questionText("TestQuestionText")
            .answerList(List.of(getIncorrectAnswer(),getCorrectAnswer()))
            .build();
}
}
