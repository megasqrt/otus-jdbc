package ru.kan.otus.tasker.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    private String description;

    @Enumerated(EnumType.ORDINAL)
    private TaskStatus status;

    @Enumerated(EnumType.ORDINAL)
    private Priority priority;

    public Task(String title) {
        this.title = title;
        this.description = "";
        this.status = TaskStatus.NEW;
        this.priority = Priority.LOW;
    }

    public enum Priority {
        LOW, MEDIUM, HIGH;
    }

}
