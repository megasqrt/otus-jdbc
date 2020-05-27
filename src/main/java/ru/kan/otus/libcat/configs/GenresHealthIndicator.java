package ru.kan.otus.libcat.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.kan.otus.libcat.repositories.BooksRepository;

@RequiredArgsConstructor
@Component
public class GenresHealthIndicator implements HealthIndicator {

    private final BooksRepository booksRepository;

    @Override
    public Health health() {
        int count = booksRepository.findAll().size();
        if (count > 0) {
            return Health.up().status(Status.UP).build();
        } else {
            return Health.down().status(Status.DOWN).build();
        }
    }
}
