package ru.otus.lyamin.app.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.lyamin.app.dao.QuestionDao;
import ru.otus.lyamin.app.entity.Question;
import ru.otus.lyamin.app.service.impl.QuestionServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static ru.otus.lyamin.app.prototype.QuestionPrototype.getQuestion;

class QuestionServiceImplTest {

    private final QuestionDao questionDao = mock(QuestionDao.class);
    private final QuestionServiceImpl questionService = new QuestionServiceImpl(questionDao);
    private List<Question> questionList;


    @BeforeEach
    void setUp() {
        questionList = new ArrayList<>();
        questionList.add(getQuestion());
    }

    @Test
    void getQuestions() {
        when(questionDao.getQuestions()).thenReturn(questionList);
        List<Question> list = questionService.getQuestions();
        assertEquals(list, questionList);
        verify(questionDao, times(1)).getQuestions();
    }
}