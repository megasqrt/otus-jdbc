package ru.kan.otus.tasker.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GenresHealthIndicator /*implements HealthIndicator */ {

    /*private final TaskRepository taskRepository;

    @Override
    public Health health() {
        int count = taskRepository.findAll().size();
        if (count > 0) {
            return Health.up().status(Status.UP).build();
        } else {
            return Health.down().status(Status.DOWN).build();
        }
    }*/
}
