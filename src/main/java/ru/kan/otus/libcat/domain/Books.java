package ru.kan.otus.libcat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/*https://www.baeldung.com/spring-data-mongodb-index-annotations-converter*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Books")
public class Books {

    @Id
    private String id;

    private String title;

    private Authors author;

    private Genres genre;

    @Field("Comments")
    private List<Comments> comment;

    public Books(String title, Authors authors, Genres genres) {
        this.title = title;
        this.author = authors;
        this.genre = genres;
    }
}
