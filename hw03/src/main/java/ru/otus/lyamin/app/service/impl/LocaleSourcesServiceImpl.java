package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.config.LocaleProvider;
import ru.otus.lyamin.app.service.interf.LocaleSourcesService;

@Service
@AllArgsConstructor
public class LocaleSourcesServiceImpl implements LocaleSourcesService {
    private final LocaleProvider localeProvider;
    private final MessageSource messageSource;

    public String getLocalizeMessage(String messageLabel, Object... params) {
        return messageSource.getMessage(messageLabel, params, localeProvider.getCurrentLocale());
    }
}
