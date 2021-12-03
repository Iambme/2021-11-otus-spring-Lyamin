package ru.otus.lyamin.app.prototype;

import lombok.experimental.UtilityClass;
import ru.otus.lyamin.app.entity.Answer;


@UtilityClass
public class AnswerPrototype {

    public static Answer getCorrectAnswer(){
        return new Answer("testCorrectAnswerText",true,1);
    }
    public static Answer getIncorrectAnswer(){
        return new Answer("testCorrectAnswerText",false,2);
    }
}
