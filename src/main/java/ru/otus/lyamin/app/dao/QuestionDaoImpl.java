package ru.otus.lyamin.app.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;
import ru.otus.lyamin.app.entity.Answer;
import ru.otus.lyamin.app.entity.Question;
import ru.otus.lyamin.app.exception.QuestionLoadingException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class QuestionDaoImpl implements QuestionDao {
    private final Resource questionsResource;
    private String[] headers;

    @Override
    public List<Question> getQuestions() {
        List<Question> questionList = new ArrayList<>();
        List<Answer> answerList;

        try (Reader in = new InputStreamReader((questionsResource.getInputStream()))) {
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
                Collections.shuffle(answerList);
                questionList.add(new Question(record.get(headers[0]), answerList));

            }
        } catch (IOException e) {
            throw new QuestionLoadingException("Error while loading questions",e);
        }

        return questionList;

    }
}
