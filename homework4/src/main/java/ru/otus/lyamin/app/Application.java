package ru.otus.lyamin.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.lyamin.app.service.interf.ExamService;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        ExamService examService = applicationContext.getBean(ExamService.class);
        examService.startExam();
    }
}