package ru.otus.lyamin.app.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.lyamin.app.service.interf.ExamService;
import ru.otus.lyamin.app.service.interf.LocaleSourcesService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Класс AppShellController")
@SpringBootTest(classes = AppShellController.class, properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
class AppShellControllerTest {

    @MockBean
    LocaleSourcesService localeSourcesService;
    @MockBean
    ExamService examService;
    @Autowired
    AppShellController appShellController;

    @DisplayName("корректно приветствует при логине")
    @Test
    void login() {
        String user = "TestUser";
        appShellController.login(user);
        verify(localeSourcesService, times(1)).getLocalizeMessage("shell.hello", user);
    }

    @DisplayName("корректно приветствует при логине")
    @Test
    void startQuiz() {
        appShellController.startQuiz();
        verify(examService, times(1)).startExam();
        verify(localeSourcesService, times(1)).getLocalizeMessage("shell.goodbye");
    }
}