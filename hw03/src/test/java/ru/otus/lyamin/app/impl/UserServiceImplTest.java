package ru.otus.lyamin.app.impl;

import org.junit.jupiter.api.Test;
import ru.otus.lyamin.app.entity.User;
import ru.otus.lyamin.app.service.impl.UserServiceImpl;
import ru.otus.lyamin.app.service.interf.ReadWriteService;
import ru.otus.lyamin.app.service.interf.UserService;
import ru.otus.lyamin.app.service.interf.WriteWithLocalizationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static ru.otus.lyamin.app.prototype.UserPrototype.getTestUser;

class UserServiceImplTest {
    private final ReadWriteService readWriteService = mock(ReadWriteService.class);
    private final WriteWithLocalizationService writeWithLocalizationService = mock(WriteWithLocalizationService.class);
    private final UserService userService = new UserServiceImpl(readWriteService, writeWithLocalizationService);

    @Test
    void getUser() {
        when(readWriteService.readString()).thenReturn("test");
        User testUser = userService.getUser();
        assertEquals(getTestUser(), testUser);
        verify(readWriteService, times(2)).readString();
    }
}
