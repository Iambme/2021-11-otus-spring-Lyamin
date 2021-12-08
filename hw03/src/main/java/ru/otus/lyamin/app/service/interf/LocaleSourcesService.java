package ru.otus.lyamin.app.service.interf;

public interface LocaleSourcesService {
    String getLocalizeMessage(String messageLabel, Object... params);

    String getLocalizeMessage(String messageLabel);
}
