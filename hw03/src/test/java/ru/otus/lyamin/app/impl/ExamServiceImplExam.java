package ru.otus.lyamin.app.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.lyamin.app.config.AppConfig;
import ru.otus.lyamin.app.service.impl.ExamServiceImpl;
import ru.otus.lyamin.app.service.impl.QuestionServiceImpl;
import ru.otus.lyamin.app.service.impl.ReadWriteServiceImpl;
import ru.otus.lyamin.app.service.impl.UserServiceImpl;
import ru.otus.lyamin.app.service.interf.*;

import java.util.List;

import static org.mockito.Mockito.*;
import static ru.otus.lyamin.app.prototype.QuestionPrototype.getQuestion;
import static ru.otus.lyamin.app.prototype.UserPrototype.getTestUser;

class ExamServiceImplExam {
    private final QuestionService questionService = mock(QuestionServiceImpl.class);
    private final ReadWriteService readWriteService = mock(ReadWriteServiceImpl.class);
    private final UserService userService = mock(UserServiceImpl.class);
    private final AppConfig appConfig = mock(AppConfig.class);
    LocalizationService localizationService = mock(LocalizationService.class);
    private  ExamService examService;


    @BeforeEach
    void setUp() {
        appConfig.setSuccessScore(1);
        examService = new ExamServiceImpl(questionService,readWriteService,userService, appConfig,localizationService);
    }

    @Test
    void startExam() {
        when(readWriteService.readInt()).thenReturn(1);
        when(userService.getUser()).thenReturn(getTestUser());
        when(questionService.getQuestions()).thenReturn(List.of(getQuestion(),getQuestion()));
        examService.startExam();
        verify(readWriteService,atLeastOnce()).writeString(any());
        verify(readWriteService,times(3)).readInt();
        verify(readWriteService,never()).readString();

    }
}