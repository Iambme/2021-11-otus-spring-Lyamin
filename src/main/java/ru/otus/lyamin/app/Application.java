package ru.otus.lyamin.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.lyamin.app.presentation.Presentation;
import ru.otus.lyamin.app.presentation.PresentationImpl;

public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        Presentation presentation = applicationContext.getBean("presentation", PresentationImpl.class);
        presentation.runPresentation();


    }
}
