package ru.otus.lyamin.app.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.entity.*;
import ru.otus.lyamin.app.service.interf.QuestionService;
import ru.otus.lyamin.app.service.interf.ReadWriteService;
import ru.otus.lyamin.app.service.interf.ExamService;
import ru.otus.lyamin.app.service.interf.UserService;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    private final QuestionService questionService;
    private final ReadWriteService readWriteService;
    private final UserService userService;
    private final int successScore;

    public ExamServiceImpl(QuestionService questionService, ReadWriteService readWriteService, UserService userService, @Value("${success.score}") int successScore) {
        this.questionService = questionService;
        this.readWriteService = readWriteService;
        this.userService = userService;
        this.successScore = successScore;
    }

    private Exam buildExam() {
        User user = userService.getUser();
        List<Question> questionList = questionService.getQuestions();
        return Exam.builder()
                .user(user)
                .examResult(new ExamResult())
                .questionList(questionList)
                .successScore(successScore)
                .build();
    }

    @Override
    public void startExam() {
        Exam exam = buildExam();
        exam.getQuestionList().forEach(question -> {
            boolean answerResult = askQuestion(question);
            if (answerResult) {
                exam.getExamResult().incrementCorrectAnswers();
            }
        });
        checkPassed(exam);
        printResult(exam);
    }

    private void checkPassed(Exam exam) {
        if (exam.getExamResult().getCorrectAnswers() > successScore) {
            exam.getExamResult().setPassed(true);
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

    private void printResult(Exam exam) {

        readWriteService.writeString("Exam result for %s %s: %s, correct answers - %d, success score - %d.",
                exam.getUser().getSurname(), exam.getUser().getName(), exam.getExamResult().isPassed()
                        ? "pass" : "not pass", exam.getExamResult().getCorrectAnswers(), successScore);
    }

    private int readAnswer() {
        int answerNumber;
        do {
            answerNumber = readWriteService.readInt();
            if (answerNumber < 0 || answerNumber > 4) {
                readWriteService.writeString("Number must be from 1 to 4");
            }
        }
        while (answerNumber < 0 || answerNumber > 4);

        return answerNumber;
    }
}
