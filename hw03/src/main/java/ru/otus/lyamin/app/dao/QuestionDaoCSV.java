package ru.otus.lyamin.app.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import ru.otus.lyamin.app.config.AppConfig;
import ru.otus.lyamin.app.entity.Answer;
import ru.otus.lyamin.app.entity.Question;
import ru.otus.lyamin.app.exception.QuestionLoadingException;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
public class QuestionDaoCSV implements QuestionDao {

    private final AppConfig appConfig;

    public QuestionDaoCSV(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questionList = new ArrayList<>();
        List<Answer> answerList;
        String[] headers = appConfig.getHeaders();
        try (Reader in = new InputStreamReader((appConfig.getQuestions().getInputStream()))) {
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

                questionList.add(new Question(record.get(headers[0]), prepareAnswerList(answerList)));
            }
        } catch (Exception e) {
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
