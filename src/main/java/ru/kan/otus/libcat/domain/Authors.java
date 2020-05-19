package ru.kan.otus.libcat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Authors")
public class Authors {
    @Id
    private String id;
    private String fullName;

    public Authors(String fullName) {
        this.fullName = fullName;
    }
}
