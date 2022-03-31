package ru.otus.lyamin.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.domain.JavaMiddleDeveloper;
import ru.otus.lyamin.app.domain.JavaSeniorDeveloper;


@Service
@Slf4j
public class HardWorkingService {
    public JavaSeniorDeveloper hardWorking(JavaMiddleDeveloper javaMiddleDeveloper) throws InterruptedException {
        log.warn("The developer {} started to work hard", javaMiddleDeveloper.getName());
        Thread.sleep(5000);
        log.warn("Now the developer {} upgraded his skills", javaMiddleDeveloper.getName());
        return new JavaSeniorDeveloper(javaMiddleDeveloper.getName());
    }
}
