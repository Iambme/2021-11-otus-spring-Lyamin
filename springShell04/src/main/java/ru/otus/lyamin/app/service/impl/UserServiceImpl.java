package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.entity.User;
import ru.otus.lyamin.app.service.interf.UserService;
import ru.otus.lyamin.app.service.interf.WriteWithLocalizationService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final WriteWithLocalizationService writeWithLocalizationService;

    @Override
    public User getUser() {
        return new User(readName(), readSurname());
    }

    private String readSurname() {
        writeWithLocalizationService.writeWithLocalization("user.enter.surname");
        return writeWithLocalizationService.readString();
    }

    private String readName() {
        writeWithLocalizationService.writeWithLocalization("user.enter.name");
        return writeWithLocalizationService.readString();
    }
}
