package ru.kan.otus.libcat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/*https://www.baeldung.com/spring-data-mongodb-index-annotations-converter*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Books {

    @Id
    private String id;
    @Field(name = "title")
    private String title;
    @DBRef
    private Authors author;
    @DBRef
    private Genres genre;


    /*public Books(String title, Authors authors, Genres genres) {
        this.title = title;
        this.author = authors;
        this.genre = genres;
    }

    public Books(String title,String authors,String genres){
        this.title=title;
        this.author=new Authors(authors);
        this.genre=new Genres(genres);
    }*/

}
