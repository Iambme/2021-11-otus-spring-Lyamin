package ru.otus.lyamin.app.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.config.ExamConfig;
import ru.otus.lyamin.app.entity.Answer;
import ru.otus.lyamin.app.entity.Exam;
import ru.otus.lyamin.app.entity.ExamResult;
import ru.otus.lyamin.app.entity.Question;
import ru.otus.lyamin.app.service.interf.*;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    private final QuestionService questionService;
    private final ReadWriteService readWriteService;
    private final UserService userService;
    private final ExamConfig examConfig;
    private final WriteWithLocalizationService writeWithLocalizationService;

    public ExamServiceImpl(QuestionService questionService, ReadWriteService readWriteService, UserService userService, ExamConfig examConfig, WriteWithLocalizationService writeWithLocalizationService) {
        this.questionService = questionService;
        this.readWriteService = readWriteService;
        this.userService = userService;
        this.examConfig = examConfig;
        this.writeWithLocalizationService = writeWithLocalizationService;
    }

    @Override
    public void startExam() {
        Exam exam = new Exam(questionService.getQuestions(), examConfig.getSuccessScore());
        ExamResult examResult = new ExamResult(userService.getUser(), exam);

        examResult.getExam().getQuestionList().forEach(question -> {
            boolean isCorrectAnswerResult = askQuestion(question);
            examResult.applyAnswer(isCorrectAnswerResult);
        });
        checkPassed(examResult);
        printResult(examResult);
    }

    private void checkPassed(ExamResult examResult) {
        if (examResult.getCorrectAnswers() >= examConfig.getSuccessScore()) {
            examResult.setPassed(true);
        }
    }

    private boolean askQuestion(Question question) {
        writeQuestion(question.getQuestionText());
        List<Answer> answers = question.getAnswerList();
        for (Answer value : answers) {
            writeAnswer(value.getNumber(), value.getAnswerText());
        }
        int inputAnswerNumber = readAnswer();
        Answer answer = answers.get(inputAnswerNumber - 1);
        return answer.isCorrect();
    }

    private void writeAnswer(int answerIndex, String answer) {
        readWriteService.writeString(answerIndex + " - " + answer);
    }

    private void writeQuestion(String question) {
        readWriteService.writeString(question);
    }

    private void printResult(ExamResult examResult) {
        writeWithLocalizationService.writeWithLocalization("exam.result", examResult.getUser().getSurname(),
                examResult.getUser().getName(), examResult.getCorrectAnswers(), examConfig.getSuccessScore());
        writeWithLocalizationService.writeWithLocalization(examResult.isPassed() ? "exam.pass" : "exam.not.pass");
    }

    private int readAnswer() {
        int answerNumber;
        do {
            answerNumber = readWriteService.readInt();
            if (answerNumber < 0 || answerNumber > 4) {
                writeWithLocalizationService.writeWithLocalization("exam.questions.count", 1, 4);
            }
        }
        while (answerNumber < 0 || answerNumber > 4);

        return answerNumber;
    }

}
