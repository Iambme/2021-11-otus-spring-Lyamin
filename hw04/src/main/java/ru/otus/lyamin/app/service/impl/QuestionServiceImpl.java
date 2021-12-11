package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.dao.QuestionDao;
import ru.otus.lyamin.app.entity.Question;
import ru.otus.lyamin.app.service.interf.QuestionService;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
   private final QuestionDao questionDao;

    @Override
    public List<Question> getQuestions() {
        return questionDao.getQuestions();
    }
}
