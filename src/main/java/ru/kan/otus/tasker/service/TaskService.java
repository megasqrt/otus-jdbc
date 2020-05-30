package ru.kan.otus.tasker.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kan.otus.tasker.domain.Task;
import ru.kan.otus.tasker.repositories.TaskRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepo;

    @HystrixCommand(fallbackMethod = "defaultList", commandKey = "Task")
    public List<Task> findAll() {
        return taskRepo.findAll();
    }

    @HystrixCommand(fallbackMethod = "defaultVoid", commandKey = "Task")
    public void deleteById(Task task) {
        taskRepo.delete(task);
    }

    @HystrixCommand(fallbackMethod = "defaulVoid", commandKey = "Task")
    public void delete(Task task) {
        taskRepo.delete(task);
    }

    @HystrixCommand(fallbackMethod = "defaulVoid", commandKey = "Task")
    public void save(Task task) {
        taskRepo.save(task);
    }


    public List<Task> defaultList() {
        List<Task> nullTaskList = Arrays.asList(Task.builder().id(0)
                .description("NaN")
                .build());
        return nullTaskList;
    }

    public void defaultDeleteBook() {
    }

    public Optional<Task> findById(long id) {
        return taskRepo.findById(id);
    }
}
