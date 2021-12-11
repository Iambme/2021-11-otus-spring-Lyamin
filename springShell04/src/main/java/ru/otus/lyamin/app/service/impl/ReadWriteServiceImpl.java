package ru.otus.lyamin.app.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.service.interf.ReadWriteService;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Scanner;

@Service
public class ReadWriteServiceImpl implements ReadWriteService {
    private final PrintWriter out;
    private final Scanner in;

    public ReadWriteServiceImpl(@Value("#{ T(java.lang.System).out }") PrintStream printStream,
                                            @Value("#{ T(java.lang.System).in }") InputStream inputStream) {
        this.out = new PrintWriter(printStream, true);
        this.in = new Scanner(inputStream);
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
