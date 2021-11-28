package ru.otus.lyamin.app.service.interf;

import ru.otus.lyamin.app.entity.Exam;

public interface ExamService {
    Exam buildExam();

    void startExam();

}
