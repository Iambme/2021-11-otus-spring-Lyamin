package ru.otus.lyamin.app.util;

import java.util.Random;

public class ProblemsGenerator {

    public static void generateProblem(String method) {
        if (new Random().nextBoolean()) {
            System.out.println(method + " success");
            return;
        }

        if (new Random().nextBoolean()) {
            System.out.println(method + " exception problem");
            throw new IllegalStateException(method + " IllegalStateException problem");
        }

        try {
            System.out.println(method + " delayed problem");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}