package ru.otus.lyamin.app.prototype;

import lombok.experimental.UtilityClass;
import ru.otus.lyamin.app.entity.Answer;


@UtilityClass
public class AnswerPrototype {

    public static Answer getCorrectAnswer(){
        return Answer.builder()
                .answerText("testCorrectAnswerText")
                .number(1)
                .isCorrect(false)
                .build();
    }
    public static Answer getIncorrectAnswer(){
        return Answer.builder()
                .answerText("testIncorrectAnswerText")
                .number(2)
                .isCorrect(true)
                .build();
    }
}
