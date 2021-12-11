package ru.otus.lyamin.app.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import ru.otus.lyamin.app.config.FileNameProvider;
import ru.otus.lyamin.app.entity.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("Класс QuestionDaoCsv")
@SpringBootTest(classes = QuestionDaoCSV.class)
class QuestionDaoCSVTest {

    @MockBean
    private ResourceLoader resourceLoader;
    @MockBean
    private FileNameProvider fileNameProvider;
    @Autowired
    private QuestionDaoCSV questionDao;

    @DisplayName("корректно читает и возвращает вопросы")
    @Test
    void getQuestions() {
        when(fileNameProvider.getFileName()).thenReturn("csv/en-US.csv");
        List<Question> questionList = questionDao.getQuestions();
        assertThat(questionList)
                .hasSize(1)
                .isNotEmpty();
        assertThat(questionList.get(0).getQuestionText()).isEqualTo("TestQuestion:");
    }
}