package ru.otus.lyamin.app.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.otus.lyamin.app.entity.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class QuestionDaoImplExam {
private  QuestionDaoImpl questionDao ;

    @BeforeEach
    void setUp() {
        Resource path = new ClassPathResource("csv/questions.csv");
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