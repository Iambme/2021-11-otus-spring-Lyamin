package ru.otus.lyamin.app.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lyamin.app.entity.User;
import ru.otus.lyamin.app.service.impl.UserServiceImpl;
import ru.otus.lyamin.app.service.interf.ReadWriteService;
import ru.otus.lyamin.app.service.interf.UserService;
import ru.otus.lyamin.app.service.interf.WriteWithLocalizationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static ru.otus.lyamin.app.prototype.UserPrototype.getTestUser;

@DisplayName("Класс UserServiceImpl")
@SpringBootTest(classes = UserServiceImpl.class)
class UserServiceImplTest {
    @MockBean
    private ReadWriteService readWriteService;
    @MockBean
    private WriteWithLocalizationService writeWithLocalizationService;

    @Autowired
    private UserService userService;

    @DisplayName("корректно создает юзера")
    @Test
    void getUser() {
        when(readWriteService.readString()).thenReturn("test");
        User testUser = userService.getUser();
        assertEquals(getTestUser(), testUser);
        verify(readWriteService, times(2)).readString();
        verify(writeWithLocalizationService,times(2)).writeWithLocalization(any());
    }
}
