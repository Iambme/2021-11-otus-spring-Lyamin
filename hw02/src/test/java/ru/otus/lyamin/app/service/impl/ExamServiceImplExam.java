package ru.otus.lyamin.app.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.lyamin.app.service.interf.ExamService;
import ru.otus.lyamin.app.service.interf.QuestionService;
import ru.otus.lyamin.app.service.interf.ReadWriteService;
import ru.otus.lyamin.app.service.interf.UserService;

import java.util.List;

import static org.mockito.Mockito.*;
import static ru.otus.lyamin.app.prototype.QuestionPrototype.getQuestion;
import static ru.otus.lyamin.app.prototype.UserPrototype.getTestUser;

class ExamServiceImplExam {
    private final QuestionService questionService = mock(QuestionServiceImpl.class);
    private final ReadWriteService readWriteService = mock(ReadWriteServiceImpl.class);
    private final UserService userService = mock(UserServiceImpl.class);
    private  ExamService examService;

    @BeforeEach
    void setUp() {
        int testSuccessScore = 1;
        examService = new ExamServiceImpl(questionService,readWriteService,userService, testSuccessScore);
    }

    @Test
    void startExam() {
        when(readWriteService.readInt()).thenReturn(1);
        when(userService.getUser()).thenReturn(getTestUser());
        when(questionService.getQuestions()).thenReturn(List.of(getQuestion(),getQuestion()));
        examService.startExam();
        verify(readWriteService,atLeastOnce()).writeString(any());
        verify(readWriteService,times(2)).readInt();
        verify(readWriteService,never()).readString();

    }
}