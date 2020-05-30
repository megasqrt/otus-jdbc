package ru.kan.otus.tasker.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
/*@NamedEntityGraph(name = "authorAndGenre-eg", attributeNodes = {
        @NamedAttributeNode("author"),
        @NamedAttributeNode("genre")
})*/
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private long id;

    @Column(name = "lastname", nullable = false, unique = true)
    private String lastName;

    @Column(name = "firstname", nullable = false, unique = true)
    private String firstName;

    @Column(name = "middlename", nullable = false, unique = true)
    private String middleName;

    //  @OneToOne(targetEntity = Position.class)
    //@ManyToOne
    // @JoinColumn(name = "position_id")
    @Column(name = "position_id")
    private long position;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_task", joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Task> tasks;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_group", joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<EmployeeGroup> groups;

}
