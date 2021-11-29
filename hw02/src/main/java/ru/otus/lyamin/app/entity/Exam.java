package ru.otus.lyamin.app.entity;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@ToString
public class Exam {
    private final User user;
    private final List<Question> questionList;
    private final int successScore;
    ExamResult examResult;

}
