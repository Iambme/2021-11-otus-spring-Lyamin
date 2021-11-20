package ru.otus.lyamin.app.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.otus.lyamin.app.entity.Answer;
import ru.otus.lyamin.app.entity.Question;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class QuestionDaoImpl implements QuestionDao {
    private String path;
    private String[] headers;

    @Override
    public List<Question> getQuestions()  {
        List<Question> questionList = new ArrayList<>();
        List<Answer> answerList;

        try (Reader in = new FileReader(path)){
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(headers)
                    .withFirstRecordAsHeader()
                    .parse(in);
            for (CSVRecord record : records) {
                answerList = new ArrayList<>();
                answerList.add(new Answer(record.get(headers[0]), false));
                answerList.add(new Answer(record.get(headers[1]), false));
                answerList.add(new Answer(record.get(headers[2]), false));
                answerList.add(new Answer(record.get(headers[3]), true));
                Collections.shuffle(answerList);
                questionList.add(new Question(record.get("question"), answerList));

            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return questionList;

    }
}
