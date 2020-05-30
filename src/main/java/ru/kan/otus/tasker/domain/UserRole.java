package ru.kan.otus.tasker.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "userrole")
public class UserRole {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
}
