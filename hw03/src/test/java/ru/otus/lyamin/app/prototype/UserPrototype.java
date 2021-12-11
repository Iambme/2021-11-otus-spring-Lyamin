package ru.otus.lyamin.app.prototype;

import lombok.experimental.UtilityClass;
import ru.otus.lyamin.app.entity.User;

@UtilityClass
public class UserPrototype {
    public static User getTestUser() {
        return new User("test", "test");
    }
}
