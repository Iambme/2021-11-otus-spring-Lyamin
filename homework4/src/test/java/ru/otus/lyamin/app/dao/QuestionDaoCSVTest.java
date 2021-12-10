package ru.otus.lyamin.app.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import ru.otus.lyamin.app.config.FileNameProvider;
import ru.otus.lyamin.app.entity.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Класс QuestionDaoCsv")
@SpringBootTest(classes = QuestionDao.class)
class QuestionDaoCSVTest {
    private QuestionDaoCSV questionDao;
    @MockBean
    private ResourceLoader resourceLoader;
    @MockBean
    private FileNameProvider fileNameProvider;

    @BeforeEach
    void setUp() {
        Resource path = new ClassPathResource("csv/en-US.csv");
        when(resourceLoader.getResource(any())).thenReturn(path);
        questionDao = new QuestionDaoCSV(fileNameProvider, resourceLoader);
    }
    @DisplayName("корректно читает и возвращает вопросы")
    @Test
    void getQuestions() {
        List<Question> questionList = questionDao.getQuestions();
        assertThat(questionList)
                .hasSize(1)
                .isNotEmpty();
        assertThat(questionList.get(0).getQuestionText()).isEqualTo("TestQuestion:");
    }
}