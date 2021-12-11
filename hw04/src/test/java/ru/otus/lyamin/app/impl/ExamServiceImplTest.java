package ru.otus.lyamin.app.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lyamin.app.config.ExamPropertiesProvider;
import ru.otus.lyamin.app.service.impl.ExamServiceImpl;
import ru.otus.lyamin.app.service.interf.*;

import java.util.List;

import static org.mockito.Mockito.*;
import static ru.otus.lyamin.app.prototype.QuestionPrototype.getQuestion;
import static ru.otus.lyamin.app.prototype.UserPrototype.getTestUser;

@DisplayName("Класс ExamServiceImpl")
@SpringBootTest(classes = ExamServiceImpl.class)
class ExamServiceImplTest {
    @MockBean
    private QuestionService questionService;
    @MockBean
    private ReadWriteService readWriteService;
    @MockBean
    private UserService userService;
    @MockBean
    private ExamPropertiesProvider examPropertiesProvider;
    @MockBean
    private WriteWithLocalizationService writeWithLocalizationService;

    @Autowired
    private ExamService examService;

    @DisplayName("корректно запускает экзамен")
    @Test
    void startExam() {
        when(readWriteService.readInt()).thenReturn(1);
        when(userService.getUser()).thenReturn(getTestUser());
        when(questionService.getQuestions()).thenReturn(List.of(getQuestion(), getQuestion()));
        examService.startExam();
        verify(readWriteService, atLeastOnce()).writeString(any());
        verify(readWriteService, atLeastOnce()).readInt();
        verify(readWriteService, never()).readString();
        verify(examPropertiesProvider,atLeastOnce()).getSuccessScore();
        verify(writeWithLocalizationService,atLeastOnce()).writeWithLocalization(any());

    }
}