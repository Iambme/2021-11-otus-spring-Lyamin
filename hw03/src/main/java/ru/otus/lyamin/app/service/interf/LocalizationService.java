package ru.otus.lyamin.app.service.interf;

public interface LocalizationService {

    String getMessage(String messageLabel, Object ... params);
    String getMessage(String messageLabel);
}
