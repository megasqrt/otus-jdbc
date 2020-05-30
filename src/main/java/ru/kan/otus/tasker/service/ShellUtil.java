package ru.kan.otus.tasker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;


@ShellComponent
public class ShellUtil {

    @Autowired
    private EmailService emailEmitterService;

    @ShellMethod(value = "Send task", key = {"send", "s"})
    public void sendTask(@ShellOption int count) {
        emailEmitterService.sendEmails(count);
    }
}