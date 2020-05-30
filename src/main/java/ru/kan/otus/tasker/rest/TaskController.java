package ru.kan.otus.tasker.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kan.otus.tasker.domain.Task;
import ru.kan.otus.tasker.domain.TaskStatus;
import ru.kan.otus.tasker.repositories.EmployeeRepository;
import ru.kan.otus.tasker.service.TaskService;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskRepo;
    private final EmployeeRepository authorRepo;

    @GetMapping("/task")
    public String listPage(Model model) {
        List<Task> taskList = taskRepo.findAll();
        model.addAttribute("taskList", taskList);
        return "task";
    }

    @GetMapping("/task/add")
    public String showAddPage(Model model) {
        return "addTask";
    }

    @PostMapping("/task/add")
    public String addTask(@ModelAttribute Task task, Model model) {
        task.setStatus(TaskStatus.NEW);
        taskRepo.save(task);
        return "redirect:/task";
    }

    @GetMapping("/task/edit")
    public String showEditPage(@RequestParam(value = "id", required = true) long id, Model model) {
        Optional<Task> task = taskRepo.findById(id);
        model.addAttribute("task", task.get());
        return "editTask";
    }

    @PostMapping("/task/edit")
    public String editTask(@RequestParam(value = "id", required = true) long id,
                           @ModelAttribute Task task, Model model) {
        taskRepo.save(task);
        return "redirect:/task";
    }

    @PostMapping("/task/delete")
    public String deletePost(@RequestParam(value = "id", required = true) long id,
                             @ModelAttribute Task task, Model model) {
        taskRepo.delete(task);
        return "redirect:/task";
    }
}
