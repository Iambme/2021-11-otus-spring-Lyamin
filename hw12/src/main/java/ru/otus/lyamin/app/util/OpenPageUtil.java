package ru.otus.lyamin.app.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class OpenPageUtil {
    public static void openHomePage() {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:8989");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
