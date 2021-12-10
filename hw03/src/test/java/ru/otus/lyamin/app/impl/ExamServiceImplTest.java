package ru.otus.lyamin.app.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.lyamin.app.config.ExamConfig;
import ru.otus.lyamin.app.service.impl.ExamServiceImpl;
import ru.otus.lyamin.app.service.impl.UserServiceImpl;
import ru.otus.lyamin.app.service.interf.*;

import java.util.List;

import static org.mockito.Mockito.*;
import static ru.otus.lyamin.app.prototype.QuestionPrototype.getQuestion;
import static ru.otus.lyamin.app.prototype.UserPrototype.getTestUser;

class ExamServiceImplTest {
    private final QuestionService questionService = mock(QuestionService.class);
    private final ReadWriteService readWriteService = mock(ReadWriteService.class);
    private final UserService userService = mock(UserServiceImpl.class);
    private final ExamConfig examConfig = mock(ExamConfig.class);
    private final WriteWithLocalizationService writeWithLocalizationService = mock(WriteWithLocalizationService.class);
    private ExamService examService;


    @BeforeEach
    void setUp() {
        examConfig.setSuccessScore(1);
        examService = new ExamServiceImpl(questionService, readWriteService, userService, examConfig, writeWithLocalizationService);
    }

    @Test
    void startExam() {
        when(readWriteService.readInt()).thenReturn(1);
        when(userService.getUser()).thenReturn(getTestUser());
        when(questionService.getQuestions()).thenReturn(List.of(getQuestion(), getQuestion()));
        examService.startExam();
        verify(readWriteService, atLeastOnce()).writeString(any());
        verify(readWriteService, times(2)).readInt();
        verify(readWriteService, never()).readString();

    }
}