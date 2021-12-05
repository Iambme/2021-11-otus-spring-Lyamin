package ru.otus.lyamin.app.service.interf;

public interface WriteWithLocalizationService {
    void writeWithLocalization(String messageLabel, Object... params);
    void writeWithLocalization(String messageLabel);
}
