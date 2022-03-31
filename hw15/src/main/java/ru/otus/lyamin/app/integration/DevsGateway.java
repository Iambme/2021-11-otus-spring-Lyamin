package ru.otus.lyamin.app.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.lyamin.app.domain.JavaMiddleDeveloper;
import ru.otus.lyamin.app.domain.JavaSeniorDeveloper;


import java.util.Collection;

@MessagingGateway
public interface DevsGateway {
    @Gateway(requestChannel = "middleDevsChannel", replyChannel = "seniorDevsChannel")
    Collection<JavaSeniorDeveloper> process(Collection<JavaMiddleDeveloper> javaMiddleDevelopers);
}
