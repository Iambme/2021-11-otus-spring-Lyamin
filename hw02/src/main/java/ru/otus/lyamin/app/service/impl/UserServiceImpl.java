package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.entity.User;
import ru.otus.lyamin.app.service.interf.UserService;
import ru.otus.lyamin.app.service.interf.ReadWriteService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final ReadWriteService readWriteService;

    @Override
    public User getUser() {
        return new User(readName(), readSurname());
    }

    private String readSurname() {
        readWriteService.writeString("Your name : ");
        return readWriteService.readString();
    }

    private String readName() {
        readWriteService.writeString("Your surname : ");
        return readWriteService.readString();
    }
}
