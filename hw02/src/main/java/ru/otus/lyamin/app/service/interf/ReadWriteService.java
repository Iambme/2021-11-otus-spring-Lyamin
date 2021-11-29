package ru.otus.lyamin.app.service.interf;

import org.springframework.lang.NonNull;

import java.util.Objects;

public interface ReadWriteService {
    void writeString(String outputString);

    String readString();

    int readInt();

    void writeString(String out, Object... args);
}
