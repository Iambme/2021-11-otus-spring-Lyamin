package ru.otus.lyamin.app.service;

import lombok.AllArgsConstructor;
import ru.otus.lyamin.app.dao.QuestionDao;
import ru.otus.lyamin.app.entity.Question;

import java.util.List;

@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
   private final QuestionDao questionDao;

    @Override
    public List<Question> getQuestions() {
        return questionDao.getQuestions();
    }
}
