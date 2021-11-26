package ru.otus.lyamin.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.lyamin.app.service.PresentationService;
import ru.otus.lyamin.app.service.PresentationServiceImpl;

public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        PresentationService presentationService = applicationContext.getBean("presentationService", PresentationServiceImpl.class);
        presentationService.runPresentation();



    }
}
