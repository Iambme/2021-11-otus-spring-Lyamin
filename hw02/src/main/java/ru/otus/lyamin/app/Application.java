package ru.otus.lyamin.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.lyamin.app.service.interf.ExamService;

@PropertySource("classpath:application.properties")
@ComponentScan
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        ExamService examService = applicationContext.getBean(ExamService.class);
        examService.startExam();





    }
}
