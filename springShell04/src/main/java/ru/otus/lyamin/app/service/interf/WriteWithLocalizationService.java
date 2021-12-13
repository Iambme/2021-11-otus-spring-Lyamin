package ru.otus.lyamin.app.service.interf;

public interface WriteWithLocalizationService {
    void writeWithLocalization(String messageLabel, Object... params);
    void writeWithLocalization(String messageLabel);
    void writeString(String outputString);

    String readString();

    int readInt();

    void writeString(String out, Object... args);
}
