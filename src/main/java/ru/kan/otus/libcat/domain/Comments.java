package ru.kan.otus.libcat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Comments")
public class Comments {
    @Id
    private String id;
    private String text;
    @DBRef
    @Field(name = "book")
    private Books book;

    @Override
    public String toString() {
        return "{id=" + id + ",text=" + text + ",bookId=" + book.getId() + "}";
    }
}
