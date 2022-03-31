package ru.otus.lyamin.app.integration;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.domain.JavaMiddleDeveloper;
import ru.otus.lyamin.app.domain.JavaSeniorDeveloper;

import java.util.Collection;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.RandomUtils.nextInt;

@Service
@RequiredArgsConstructor
@Slf4j
public class IntegrationService {

    private final DevsGateway devsGateway;

    @Async
    @Scheduled(fixedRate = 1000)
    public void start() {
        Collection<JavaMiddleDeveloper> javaMiddleDevelopers = generateJavaMiddleDevelopers();
        log.warn("*** Java Middle Developers: " + javaMiddleDevelopers.stream()
                .map(JavaMiddleDeveloper::toString)
                .collect(joining(",")));

        Collection<JavaSeniorDeveloper> javaSeniorDevelopers = devsGateway.process(javaMiddleDevelopers);
        log.warn("*** Java Senior Developers: " + javaSeniorDevelopers.stream()
                .map(JavaSeniorDeveloper::toString)
                .collect(joining(",")));
    }

    private static Collection<JavaMiddleDeveloper> generateJavaMiddleDevelopers() {
        return IntStream.range(0, nextInt(1, 5))
                .mapToObj(i -> new JavaMiddleDeveloper(new Faker().name().fullName()))
                .collect(toList());
    }
}
