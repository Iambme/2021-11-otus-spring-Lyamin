package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.service.interf.ReadWriteService;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
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
    public void writeString(String out) {
        this.out.println(out);
    }

    @Override
    public String readString() {
        return in.nextLine();
    }

    @Override
    public int readInt() {
        return in.nextInt();
    }
}
