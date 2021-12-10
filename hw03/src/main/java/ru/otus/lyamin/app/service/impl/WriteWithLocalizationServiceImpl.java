package ru.otus.lyamin.app.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.service.interf.LocaleSourcesService;
import ru.otus.lyamin.app.service.interf.ReadWriteService;
import ru.otus.lyamin.app.service.interf.WriteWithLocalizationService;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Scanner;

@Service
public class WriteWithLocalizationServiceImpl implements WriteWithLocalizationService, ReadWriteService {
    private final LocaleSourcesService localeSourcesService;
    private final PrintWriter out;
    private final Scanner in;

    public WriteWithLocalizationServiceImpl(@Value("#{ T(java.lang.System).out }") PrintStream printStream,
                                            @Value("#{ T(java.lang.System).in }") InputStream inputStream, LocaleSourcesService localeSourcesService) {
        this.localeSourcesService = localeSourcesService;
        this.out = new PrintWriter(printStream, true);
        this.in = new Scanner(inputStream);
    }

    public void writeWithLocalization(String messageLabel, Object... params) {
        writeString(localeSourcesService.getLocalizeMessage(messageLabel, params));
    }

    public void writeWithLocalization(String messageLabel) {
        writeWithLocalization(messageLabel, (Object) null);
    }

    @Override
    public void writeString(String outputString) {
        this.out.println(outputString);
    }

    @Override
    public String readString() {
        return in.nextLine();
    }

    @Override
    public int readInt() {
        return in.nextInt();
    }

    @Override
    public void writeString(String out, Object... args) {
        Formatter f = new Formatter();
        f.format(out, args);
        this.out.println(f);
    }
}
