package ru.otus.lyamin.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Locale;
import java.util.Objects;


@EnableConfigurationProperties
@Configuration
@ConfigurationProperties("locale")
@Data
public class LocaleConfig {
    private static final String FILE_PATH = "csv/";
    private static final String FILE_EXTENSION = ".csv";
    private Locale locale;

    public LocaleConfig(Environment environment) {
        locale = Locale.forLanguageTag(Objects.requireNonNull(environment.getProperty("locale.i18n")));
    }

    private String i18n;

    public String getI18n() {
        return FILE_PATH + i18n + FILE_EXTENSION;
    }

    public void setI18n(String i18n) {
        this.i18n = i18n;
    }
}