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
@Table(name = "datatype")
public class DataType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "datatype_id")
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "value", nullable = false, unique = true)
    private String value;
}
