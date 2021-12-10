package ru.otus.lyamin.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Configuration
@Data
@ConfigurationProperties(prefix = "app")
public class AppConfig implements LocaleProvider, FileNameProvider {
    private int success;
    private String path;
    private String extension;
    private Locale locale;
    private String localeTag;

    @PostConstruct
    private void setLocalization(){
        locale =Locale.forLanguageTag(localeTag);
    }

    @Bean
    public ExamConfig examConfig() {
        return new ExamConfig(success);
    }

    @Override
    public void setCurrentLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public Locale getCurrentLocale() {
        return locale;
    }

    @Override
    public String getFileName() {
        return path + localeTag + extension;
    }
}
