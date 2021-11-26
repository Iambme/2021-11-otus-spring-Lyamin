package ru.otus.lyamin.app.dao;

import ru.otus.lyamin.app.entity.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions();
}
