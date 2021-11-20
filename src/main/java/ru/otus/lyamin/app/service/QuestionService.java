package ru.otus.lyamin.app.service;

import ru.otus.lyamin.app.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestions();
}
