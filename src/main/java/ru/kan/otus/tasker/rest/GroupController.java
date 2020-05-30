package ru.kan.otus.tasker.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kan.otus.tasker.domain.EmployeeGroup;
import ru.kan.otus.tasker.repositories.EmployeeGroupRepository;

@Controller
@RequiredArgsConstructor
public class GroupController {

    private final EmployeeGroupRepository employeeGroupRepo;


    @GetMapping("/group")
    public String listPage(Model model) {
        model.addAttribute("groupList", employeeGroupRepo.findAll());
        return "group";
    }

    @GetMapping("/group/add")
    public String showAddPage(Model model) {
        model.addAttribute("groupList", employeeGroupRepo.findAll());
        return "addGroup";
    }

    @PostMapping("/group/add")
    public String addEmployeeGroup(@ModelAttribute EmployeeGroup group, Model model) {
        employeeGroupRepo.save(group);
        return "redirect:/group";
    }

    @GetMapping("/group/edit")
    public String showEditPage(@RequestParam(value = "id", required = true) long id, Model model) {
        model.addAttribute("group", employeeGroupRepo.findById(id).orElseThrow());
        return "editGroup";
    }

    @PostMapping("/group/edit")
    public String editEmployeeGroup(@RequestParam(value = "id", required = true) long id,
                                    @ModelAttribute EmployeeGroup group, Model model) {
        employeeGroupRepo.save(group);
        return "redirect:/group";
    }

    @PostMapping("/group/delete")
    public String deletePost(@RequestParam(value = "id", required = true) long id,
                             @ModelAttribute EmployeeGroup group, Model model) {
        employeeGroupRepo.delete(group);
        return "redirect:/group";
    }
}
