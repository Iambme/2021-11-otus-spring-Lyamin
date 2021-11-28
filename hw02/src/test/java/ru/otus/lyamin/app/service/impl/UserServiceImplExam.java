package ru.otus.lyamin.app.service.impl;

import org.junit.jupiter.api.Test;
import ru.otus.lyamin.app.entity.User;
import ru.otus.lyamin.app.service.interf.ReadWriteService;
import ru.otus.lyamin.app.service.interf.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static ru.otus.lyamin.app.prototype.UserPrototype.getTestUser;

class UserServiceImplExam {
private final ReadWriteService readWriteService = mock(ReadWriteServiceImpl.class);
private final UserService userService = new UserServiceImpl(readWriteService);

    @Test
    void getUser() {
        when(readWriteService.readString()).thenReturn("test");
        User testUser = userService.getUser();
        assertEquals(getTestUser(),testUser);
        verify(readWriteService,times(2)).readString();
    }
}