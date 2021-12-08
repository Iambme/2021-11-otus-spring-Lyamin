package ru.otus.lyamin.app.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
@Data
public class AppConfig {

    @Bean
    @ConfigurationProperties(prefix = "locale")
    public LocaleProvider localeProvider(@Value("${locale.i18n}") String locale, @Value("${locale.path}") String path, @Value("${locale.extension}") String extension) {
        LocaleProvider localeProvider = new LocaleProvider(Locale.forLanguageTag(locale));
        localeProvider.setFilePath(path + locale + extension);

        return localeProvider;
    }

    @Bean
    @ConfigurationProperties(prefix = "exam")
    public ExamConfig examConfig(@Value("${exam.success-score}") int successScore) {
        return new ExamConfig(successScore);
    }
}
