package ru.kan.otus.tasker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableHystrix
@SpringBootApplication
public class TaskerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskerApplication.class);
    }
}