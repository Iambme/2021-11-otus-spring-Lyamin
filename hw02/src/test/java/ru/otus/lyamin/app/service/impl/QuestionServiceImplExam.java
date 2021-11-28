package ru.otus.lyamin.app.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.lyamin.app.dao.QuestionDao;
import ru.otus.lyamin.app.entity.Answer;
import ru.otus.lyamin.app.entity.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class QuestionServiceImplExam {

    private  final QuestionDao questionDao = mock(QuestionDao.class);
    private final QuestionServiceImpl questionService = new QuestionServiceImpl(questionDao);
    List<Question> questionList ;
    List<Answer> answerList;


    @BeforeEach
    void setUp() {
    questionList = new ArrayList<>();
    answerList = new ArrayList<>();
    answerList.add(new Answer("testAnswer",false));
    answerList.add(new Answer("testCorrectAnswer",true));
    questionList.add(new Question("testQuestion",answerList));
    }

    @Test
    void getQuestions() {
        when(questionDao.getQuestions()).thenReturn(questionList);
        List<Question> list =questionService.getQuestions();
        assertEquals(list,questionList);
        verify(questionDao,times(1)).getQuestions();
    }
}