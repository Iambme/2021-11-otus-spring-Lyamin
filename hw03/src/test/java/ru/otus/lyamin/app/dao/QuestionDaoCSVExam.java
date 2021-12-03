package ru.otus.lyamin.app.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.otus.lyamin.app.config.AppConfig;
import ru.otus.lyamin.app.entity.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuestionDaoCSVExam {
    private QuestionDaoCSV questionDao;
    private final AppConfig appConfig = mock(AppConfig.class);
    private Resource path;
    String[] headers;

    @BeforeEach
    void setUp() {
        path = new ClassPathResource("csv/questions.csv");
        headers = new String[]{"question", "answer1", "answer2", "answer3", "correctAnswer"};
        questionDao = new QuestionDaoCSV(appConfig);
    }

    @Test
    void getQuestions() {
        when(appConfig.getQuestions()).thenReturn(path);
        when(appConfig.getHeaders()).thenReturn(headers);
        List<Question> questionList = questionDao.getQuestions();
        assertThat(questionList)
                .hasSize(1)
                .isNotEmpty();
        assertThat(questionList.get(0).getQuestionText()).isEqualTo("TestQuestion:");
    }
}