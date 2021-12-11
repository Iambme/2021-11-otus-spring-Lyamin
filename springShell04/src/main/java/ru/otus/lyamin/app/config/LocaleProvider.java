package ru.otus.lyamin.app.config;

import java.util.Locale;

public interface LocaleProvider {
    void setCurrentLocale(Locale locale);
    Locale getCurrentLocale();
}
