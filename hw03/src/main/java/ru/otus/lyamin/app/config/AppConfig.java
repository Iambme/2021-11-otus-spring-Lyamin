package ru.otus.lyamin.app.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "exam")
@Data
public class AppConfig {
    private final LocaleConfig localeConfig;
    private int successScore;

    @Autowired
    public AppConfig(LocaleConfig localeConfig) {
        this.localeConfig = localeConfig;
    }

    @Bean("appLocale")
    public String appLocale() {
        return this.localeConfig.getI18n();
    }
}
