package ru.kan.otus.tasker.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kan.otus.tasker.domain.Employee;
import ru.kan.otus.tasker.repositories.EmployeeGroupRepository;
import ru.kan.otus.tasker.repositories.EmployeeRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepo;
    private final EmployeeGroupRepository employeeGroupRepo;

    @GetMapping("/employee")
    public String listPage(Model model) {
        List<Employee> employeeList = employeeRepo.findAll();
        model.addAttribute("employeeList", employeeList);
        return "employee";
    }

    @GetMapping("/employee/add")
    public String showAddPage(Model model) {
        model.addAttribute("groupList", employeeGroupRepo.findAll());
        return "addEmployee";
    }

    @PostMapping("/employee/add")
    public String addEmployee(@ModelAttribute Employee employee, Model model) {
        employeeRepo.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/employee/edit")
    public String showEditPage(@RequestParam(value = "id", required = true) long id, Model model) {
        model.addAttribute("employee", employeeRepo.findById(id).orElseThrow());
        model.addAttribute("groupList", employeeGroupRepo.findAll());
        return "editEmployee";
    }

    @PostMapping("/employee/edit")
    public String editEmployee(@RequestParam(value = "id", required = true) long id,
                               @ModelAttribute Employee employee, Model model) {
        Employee findEmploy = employeeRepo.findById(id).orElseThrow();
        employee.setTasks(findEmploy.getTasks());
        employeeRepo.save(employee);
        return "redirect:/employee";
    }

    @PostMapping("/employee/delete")
    public String deletePost(@RequestParam(value = "id", required = true) long id,
                             @ModelAttribute Employee employee, Model model) {
        employeeRepo.delete(employee);
        return "redirect:/employee";
    }
}
