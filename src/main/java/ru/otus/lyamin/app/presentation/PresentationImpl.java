package ru.otus.lyamin.app.presentation;

import lombok.AllArgsConstructor;
import ru.otus.lyamin.app.service.QuestionService;

@AllArgsConstructor
public class PresentationImpl implements Presentation{
    private QuestionService questionService;
    @Override
    public void runPresentation() {
        questionService.getQuestions().forEach(System.out::println);

    }
}
