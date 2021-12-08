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
        String[] parameters = null;
        if (params != null) {
            parameters = new String[params.length];
            for (int i = 0; i < params.length; i++) {
                parameters[i] = String.valueOf(params[i]);
            }
        }
        return messageSource.getMessage(messageLabel, parameters, localeProvider.getLocale());
    }

    public String getLocalizeMessage(String messageLabel) {
        return getLocalizeMessage(messageLabel, null);
    }
}
