package ru.otus.lyamin.app.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.config.ExamPropertiesProvider;
import ru.otus.lyamin.app.entity.Answer;
import ru.otus.lyamin.app.entity.Exam;
import ru.otus.lyamin.app.entity.ExamResult;
import ru.otus.lyamin.app.entity.Question;
import ru.otus.lyamin.app.service.interf.ExamService;
import ru.otus.lyamin.app.service.interf.QuestionService;
import ru.otus.lyamin.app.service.interf.UserService;
import ru.otus.lyamin.app.service.interf.WriteWithLocalizationService;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    private final QuestionService questionService;
    private final UserService userService;
    private final ExamPropertiesProvider examPropertiesProvider;
    private final WriteWithLocalizationService writeWithLocalizationService;

    public ExamServiceImpl(QuestionService questionService, UserService userService, ExamPropertiesProvider examPropertiesProvider, WriteWithLocalizationService writeWithLocalizationService) {
        this.questionService = questionService;

        this.userService = userService;
        this.examPropertiesProvider = examPropertiesProvider;
        this.writeWithLocalizationService = writeWithLocalizationService;
    }

    @Override
    public void startExam() {
        Exam exam = new Exam(questionService.getQuestions(), examPropertiesProvider.getSuccessScore());
        ExamResult examResult = new ExamResult(userService.getUser(), exam);

        examResult.getExam().getQuestionList().forEach(question -> {
            boolean isCorrectAnswerResult = askQuestion(question);
            examResult.applyAnswer(isCorrectAnswerResult);
        });
        checkPassed(examResult);
        printResult(examResult);
    }

    private void checkPassed(ExamResult examResult) {
        if (examResult.getCorrectAnswers() >= examPropertiesProvider.getSuccessScore()) {
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
        writeWithLocalizationService.writeString(answerIndex + " - " + answer);
    }

    private void writeQuestion(String question) {
        writeWithLocalizationService.writeString(question);
    }

    private void printResult(ExamResult examResult) {
        writeWithLocalizationService.writeWithLocalization("exam.result", examResult.getUser().getSurname(),
                examResult.getUser().getName(), examResult.getCorrectAnswers(), examPropertiesProvider.getSuccessScore());
        writeWithLocalizationService.writeWithLocalization(examResult.isPassed() ? "exam.pass" : "exam.not.pass");
    }

    private int readAnswer() {
        int answerNumber;
        do {
            answerNumber = writeWithLocalizationService.readInt();
            if (answerNumber < 0 || answerNumber > 4) {
                writeWithLocalizationService.writeWithLocalization("exam.questions.count", 1, 4);
            }
        }
        while (answerNumber < 0 || answerNumber > 4);

        return answerNumber;
    }

}
