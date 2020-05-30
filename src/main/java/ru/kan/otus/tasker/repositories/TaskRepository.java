package ru.kan.otus.tasker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.kan.otus.tasker.domain.Task;

@RepositoryRestResource(path = "task")
public interface TaskRepository extends JpaRepository<Task, Long> {
}
