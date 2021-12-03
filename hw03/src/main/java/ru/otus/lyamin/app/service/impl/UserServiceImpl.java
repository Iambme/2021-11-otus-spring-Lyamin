package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.entity.User;
import ru.otus.lyamin.app.service.interf.LocalizationService;
import ru.otus.lyamin.app.service.interf.ReadWriteService;
import ru.otus.lyamin.app.service.interf.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final ReadWriteService readWriteService;
    private final LocalizationService localizationService;

    @Override
    public User getUser() {
        return new User(readName(), readSurname());
    }

    private String readSurname() {
        readWriteService.writeString(localizationService.getMessage("user.enter.surname"));
        return readWriteService.readString();
    }

    private String readName() {
        readWriteService.writeString(localizationService.getMessage("user.enter.name"));
        return readWriteService.readString();
    }
}
