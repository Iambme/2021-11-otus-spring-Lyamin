package ru.otus.lyamin.app.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lyamin.app.config.ExamPropertiesProvider;
import ru.otus.lyamin.app.service.impl.ExamServiceImpl;
import ru.otus.lyamin.app.service.interf.ExamService;
import ru.otus.lyamin.app.service.interf.QuestionService;
import ru.otus.lyamin.app.service.interf.UserService;
import ru.otus.lyamin.app.service.interf.WriteWithLocalizationService;

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
        when(writeWithLocalizationService.readInt()).thenReturn(1);
        when(userService.getUser()).thenReturn(getTestUser());
        when(questionService.getQuestions()).thenReturn(List.of(getQuestion(), getQuestion()));
        examService.startExam();
        verify(writeWithLocalizationService, atLeastOnce()).writeString(any());
        verify(writeWithLocalizationService, atLeastOnce()).readInt();
        verify(writeWithLocalizationService, never()).readString();
        verify(examPropertiesProvider,atLeastOnce()).getSuccessScore();
        verify(writeWithLocalizationService,atLeastOnce()).writeWithLocalization(any());

    }
}