package ru.otus.lyamin.app.prototype;

import lombok.experimental.UtilityClass;
import ru.otus.lyamin.app.entity.Author;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class AuthorPrototype {
    public static Author getAuthor() {
        return new Author("1", "testAuthorFirstname1");
    }

    public static Author getAnotherAuthor() {
        return new Author("2", "testAuthorFirstname2");
    }
    public static Author getDeletableAuthor() {
        return new Author("3", "testAuthorForDelete");
    }

    public static List<Author> getAuthors() {
        return Arrays.asList(getAuthor(), getAnotherAuthor(),getDeletableAuthor());
    }
}
