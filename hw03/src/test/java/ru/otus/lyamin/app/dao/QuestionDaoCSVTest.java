package ru.otus.lyamin.app.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import ru.otus.lyamin.app.config.FileNameProvider;
import ru.otus.lyamin.app.config.LocaleProvider;
import ru.otus.lyamin.app.entity.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuestionDaoCSVTest {
    private QuestionDaoCSV questionDao;
    private final ResourceLoader resourceLoader = mock(ResourceLoader.class);
    private final FileNameProvider fileNameProvider =  mock(FileNameProvider.class);

    @BeforeEach
    void setUp() {
        Resource path = new ClassPathResource("csv/en-US.csv");
        when(resourceLoader.getResource(any())).thenReturn(path);
        questionDao = new QuestionDaoCSV(fileNameProvider, resourceLoader);
    }

    @Test
    void getQuestions() {
        List<Question> questionList = questionDao.getQuestions();
        assertThat(questionList)
                .hasSize(1)
                .isNotEmpty();
        assertThat(questionList.get(0).getQuestionText()).isEqualTo("TestQuestion:");
    }
}