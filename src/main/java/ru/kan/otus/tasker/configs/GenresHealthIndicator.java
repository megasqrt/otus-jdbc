package ru.kan.otus.tasker.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.kan.otus.tasker.repositories.TaskRepository;

@RequiredArgsConstructor
@Component
public class GenresHealthIndicator implements HealthIndicator {

    private final TaskRepository taskRepository;

    @Override
    public Health health() {
        int count = taskRepository.findAll().size();
        if (count > 0) {
            return Health.up().status(Status.UP).build();
        } else {
            return Health.down().status(Status.DOWN).build();
        }
    }
}
