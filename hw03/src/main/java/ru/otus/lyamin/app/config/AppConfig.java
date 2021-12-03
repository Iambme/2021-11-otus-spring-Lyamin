package ru.otus.lyamin.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "exam")
@Data
public class AppConfig {
    private Resource questions;
    private String[] headers;
    private int successScore;
    private String languageTag;
}
