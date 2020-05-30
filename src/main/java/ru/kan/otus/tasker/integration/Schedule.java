package ru.kan.otus.tasker.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.kan.otus.tasker.domain.Employee;
import ru.kan.otus.tasker.domain.Task;

import java.util.Collection;

@MessagingGateway
public interface Schedule {

    @Gateway(requestChannel = "taskChannel", replyChannel = "doneChannel")
    Collection<Employee> process(Task task);
}
