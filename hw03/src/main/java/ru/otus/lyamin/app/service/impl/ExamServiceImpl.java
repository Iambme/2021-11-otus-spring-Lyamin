package ru.otus.lyamin.app.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.config.AppConfig;
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
    private final AppConfig appConfig;
    private final LocalizationService localizationService;

    public ExamServiceImpl(QuestionService questionService, ReadWriteService readWriteService, UserService userService, AppConfig appConfig, LocalizationService localizationService) {
        this.questionService = questionService;
        this.readWriteService = readWriteService;
        this.userService = userService;
        this.appConfig = appConfig;
        this.localizationService = localizationService;
    }

    @Override
    public void startExam() {
        ExamResult examResult = new ExamResult();
        Exam exam = new Exam(questionService.getQuestions(), appConfig.getSuccessScore());

        examResult.setUser(userService.getUser());
        examResult.setExam(exam);

        examResult.getExam().getQuestionList().forEach(question -> {
            boolean isCorrectAnswerResult = askQuestion(question);
            examResult.applyAnswer(isCorrectAnswerResult);
        });
        checkPassed(examResult);
        printResult(examResult);
    }

    private void checkPassed(ExamResult examResult) {
        if (examResult.getCorrectAnswers() >= appConfig.getSuccessScore()) {
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
        readWriteService.writeString(localizationService.getMessage("exam.result", examResult.getUser().getSurname(),
                examResult.getUser().getName(), examResult.getCorrectAnswers(), appConfig.getSuccessScore()));
        readWriteService.writeString(localizationService.getMessage(examResult.isPassed() ? "exam.pass" : "exam.not.pass"));
    }

    private int readAnswer() {
        int answerNumber;
        do {
            answerNumber = readWriteService.readInt();
            if (answerNumber < 0 || answerNumber > 4) {
                readWriteService.writeString(localizationService.getMessage("exam.questions.count", 1, 4));
            }
        }
        while (answerNumber < 0 || answerNumber > 4);

        return answerNumber;
    }

}
