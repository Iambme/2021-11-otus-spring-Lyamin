package ru.otus.lyamin.app.service.interf;

public interface ReadWriteService {
    void writeString(String outputString);

    String readString();

    int readInt();

    void writeString(String out, Object... args);
}
