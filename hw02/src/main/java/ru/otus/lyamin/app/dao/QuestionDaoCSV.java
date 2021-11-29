package ru.otus.lyamin.app.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.otus.lyamin.app.entity.Answer;
import ru.otus.lyamin.app.entity.Question;
import ru.otus.lyamin.app.exception.QuestionLoadingException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
public class QuestionDaoCSV implements QuestionDao {
    private final Resource questions;
    private final String[] headers;

    public QuestionDaoCSV(@Value("${questions}") Resource questions, @Value("${headers}") String[] headers) {
        this.questions = questions;
        this.headers = headers;
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questionList = new ArrayList<>();
        List<Answer> answerList;

        try (Reader in = new InputStreamReader((questions.getInputStream()))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(headers)
                    .withFirstRecordAsHeader()
                    .parse(in);
            for (CSVRecord record : records) {
                answerList = new ArrayList<>();
                answerList.add(new Answer(record.get(headers[1]), false));
                answerList.add(new Answer(record.get(headers[2]), false));
                answerList.add(new Answer(record.get(headers[3]), false));
                answerList.add(new Answer(record.get(headers[4]), true));

                questionList.add(Question.builder()
                        .questionText(record.get(headers[0]))
                        .answerList(prepareAnswerList(answerList))
                        .build());
            }
        } catch (IOException e) {
            throw new QuestionLoadingException("Error while loading questions", e);
        }

        return questionList;

    }

    private List<Answer> prepareAnswerList(List<Answer> answerList) {
        Collections.shuffle(answerList);
        for (int i = 0; i < answerList.size(); i++) {
            answerList.get(i).setNumber(i + 1);
        }
        return answerList;
    }
}
