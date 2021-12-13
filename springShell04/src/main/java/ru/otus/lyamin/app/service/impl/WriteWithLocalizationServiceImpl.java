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
        readWriteService.writeString(localeSourcesService.getLocalizeMessage(messageLabel, params));
    }

    public void writeWithLocalization(String messageLabel) {
        writeWithLocalization(messageLabel, (Object) null);
    }

    @Override
    public void writeString(String outputString) {
        readWriteService.writeString(outputString);
    }

    @Override
    public String readString() {
        return readWriteService.readString();
    }

    @Override
    public int readInt() {
        return readWriteService.readInt();
    }

    @Override
    public void writeString(String out, Object... args) {
        readWriteService.writeString(out, args);
    }

}
