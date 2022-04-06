package ru.otus.lyamin.app.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.service.interf.BookService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomHealthIndicator implements HealthIndicator {
    private final BookService bookService;

    @Override
    public Health health() {
        List<Book> books = bookService.findAll();
        return books.size() > 0 ? Health.up().withDetail("There is many books in library", books).build() :
                Health.down().withDetail("message", "There is no books in library, something wrong").build();
    }
}
