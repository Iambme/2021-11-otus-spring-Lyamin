package ru.otus.lyamin.app.config;

import lombok.Data;
import java.util.Locale;

@Data
public class LocaleProvider {

    private Locale locale;
    private String filePath;

    public LocaleProvider(Locale locale) {
        this.locale = locale;
    }
}


