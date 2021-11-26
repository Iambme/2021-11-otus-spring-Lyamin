package ru.otus.lyamin.app.exception;

public class QuestionLoadingException extends RuntimeException{
    public QuestionLoadingException(String message) {
        super(message);
    }

    public QuestionLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
