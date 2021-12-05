package ru.otus.lyamin.app.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.config.LocaleConfig;
import ru.otus.lyamin.app.service.interf.ReadWriteService;
import ru.otus.lyamin.app.service.interf.WriteWithLocalizationService;

@Service
public class WriteWithLocalizationServiceImpl implements WriteWithLocalizationService {
    private final LocaleConfig localeConfig;
    private final MessageSource messageSource;
    private final ReadWriteService readWriteService;

    public WriteWithLocalizationServiceImpl(MessageSource messageSource, LocaleConfig localeConfig, ReadWriteService readWriteService) {
        this.localeConfig = localeConfig;
        this.messageSource = messageSource;
        this.readWriteService = readWriteService;

    }

    public void writeWithLocalization(String messageLabel, Object... params) {
        String[] parameters = null;
        if (params != null) {
            parameters = new String[params.length];
            for (int i = 0; i < params.length; i++) {
                parameters[i] = String.valueOf(params[i]);
            }
        }
        readWriteService.writeString(messageSource.getMessage(messageLabel, parameters, localeConfig.getLocale()));
    }
    public void writeWithLocalization(String messageLabel) {
        writeWithLocalization(messageLabel,null);
    }
}
