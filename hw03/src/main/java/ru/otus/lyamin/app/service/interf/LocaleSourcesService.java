package ru.otus.lyamin.app.service.interf;

public interface LocaleSourcesService {
    String getLocalizeMessage(String messageLabel, Object... params);
}
