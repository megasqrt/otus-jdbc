package ru.kan.otus.libcat.domain;

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
@Table(name = "BOOKS")
@NamedEntityGraph(name = "authorAndGenre-eg", attributeNodes = {
        @NamedAttributeNode("author"),
        @NamedAttributeNode("genre")
})
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @OneToOne(targetEntity = Authors.class)
    @JoinColumn(name = "author_id")
    private Authors author;

    @OneToOne(targetEntity = Genres.class)
    @JoinColumn(name = "genre_id")
    private Genres genre;
}
