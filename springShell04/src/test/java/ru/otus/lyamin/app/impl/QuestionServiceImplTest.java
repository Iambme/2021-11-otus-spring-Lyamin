package ru.otus.lyamin.app.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lyamin.app.dao.QuestionDao;
import ru.otus.lyamin.app.entity.Question;
import ru.otus.lyamin.app.service.impl.QuestionServiceImpl;
import ru.otus.lyamin.app.service.interf.QuestionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static ru.otus.lyamin.app.prototype.QuestionPrototype.getQuestion;

@DisplayName("Класс QuestionServiceImpl")
@SpringBootTest(classes = QuestionServiceImpl.class)
class QuestionServiceImplTest {

    @MockBean
    private QuestionDao questionDao;
    @Autowired
    private QuestionService questionService;
    private List<Question> questionList;


    @BeforeEach
    void setUp() {
        questionList = new ArrayList<>();
        questionList.add(getQuestion());
    }

    @DisplayName("корректно возвращает вопросы из дао")
    @Test
    void getQuestions() {
        when(questionDao.getQuestions()).thenReturn(questionList);
        List<Question> list = questionService.getQuestions();
        assertEquals(list, questionList);
        verify(questionDao, times(1)).getQuestions();
    }
}