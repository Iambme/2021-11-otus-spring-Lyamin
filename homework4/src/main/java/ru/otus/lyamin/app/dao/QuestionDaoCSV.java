package ru.otus.lyamin.app.dao;

import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import ru.otus.lyamin.app.config.FileNameProvider;
import ru.otus.lyamin.app.entity.Answer;
import ru.otus.lyamin.app.entity.Question;
import ru.otus.lyamin.app.exception.QuestionLoadingException;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
@AllArgsConstructor
public class QuestionDaoCSV implements QuestionDao {
    private final ResourceLoader resourceLoader;
    private final FileNameProvider fileNameProvider;

    @Override
    public List<Question> getQuestions() {
        List<Question> questionList = new ArrayList<>();
        List<Answer> answerList;
        try (Reader in = new InputStreamReader((getResourceCSV().getInputStream()))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(in);
            for (CSVRecord record : records) {
                answerList = new ArrayList<>();
                answerList.add(new Answer(record.get(1), false));
                answerList.add(new Answer(record.get(2), false));
                answerList.add(new Answer(record.get(3), false));
                answerList.add(new Answer(record.get(4), true));

                questionList.add(new Question(record.get(0), prepareAnswerList(answerList)));
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

    private Resource getResourceCSV() {
        return resourceLoader.getResource(fileNameProvider.getFileName());
    }
}
