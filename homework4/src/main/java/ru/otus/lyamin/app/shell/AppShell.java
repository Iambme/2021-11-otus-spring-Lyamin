
package ru.otus.lyamin.app.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.lyamin.app.service.interf.ExamService;
import ru.otus.lyamin.app.service.interf.LocaleSourcesService;

import static org.springframework.shell.Availability.available;
import static org.springframework.shell.Availability.unavailable;

@ShellComponent
@RequiredArgsConstructor
public class AppShell {
    private final LocaleSourcesService localeSourcesService;
    private final ExamService examService;
    private String userName;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "AnyUser") String userName) {
        this.userName = userName;
        return localeSourcesService.getLocalizeMessage("shell.hello", userName);
    }

    @ShellMethod(value = "Start quiz", key = {"s", "start"})
    @ShellMethodAvailability(value = "isUserLoggedIn")
    public String startQuiz() {
        examService.startExam();
        return localeSourcesService.getLocalizeMessage("shell.goodbye");
    }

    private Availability isUserLoggedIn() {
        return userName == null ? unavailable(localeSourcesService.getLocalizeMessage("shell.need-to-login")) : available();
    }
}
