package ru.otus.lyamin.app.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        return veryImportantCheck() ? Health.up().withDetail("message", "Look like everything is fine").build() :
                Health.down().withDetail("message", "Something wrong").build();
    }

    private boolean veryImportantCheck() {
        return new Random().nextBoolean();
    }
}
