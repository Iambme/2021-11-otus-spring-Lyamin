package ru.otus.lyamin.app.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.lyamin.app.entity.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class QuestionDaoImplTest {
private  QuestionDaoImpl questionDao ;

    @BeforeEach
    void setUp() {
        String path = "src/test/java/ru/otus/lyamin/app/resources/questions.csv";
        String[] headers = new String[]{"question", "answer1", "answer2", "answer3", "correctAnswer"};
        questionDao = new QuestionDaoImpl(path, headers);
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