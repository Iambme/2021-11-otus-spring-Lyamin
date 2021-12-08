package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.service.interf.LocaleSourcesService;
import ru.otus.lyamin.app.service.interf.ReadWriteService;
import ru.otus.lyamin.app.service.interf.WriteWithLocalizationService;

@Service
@AllArgsConstructor
public class WriteWithLocalizationServiceImpl implements WriteWithLocalizationService {
    private final LocaleSourcesService localeSourcesService;
    private final ReadWriteService readWriteService;


    public void writeWithLocalization(String messageLabel, Object... params) {
        String[] parameters = null;
        if (params != null) {
            parameters = new String[params.length];
            for (int i = 0; i < params.length; i++) {
                parameters[i] = String.valueOf(params[i]);
            }
        }
        readWriteService.writeString(localeSourcesService.getLocalizeMessage(messageLabel, (Object[]) parameters));
    }

    public void writeWithLocalization(String messageLabel) {
        writeWithLocalization(messageLabel, (Object) null);
    }
}
