package ru.otus.lyamin.app.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PresentationServiceImpl implements PresentationService {
    private final QuestionService questionService;

    @Override
    public void runPresentation() {
        questionService.getQuestions().forEach(System.out::println);

    }
}
