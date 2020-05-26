package ru.kan.otus.libcat.config.changelog;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.kan.otus.libcat.domain.Authors;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.domain.Genres;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    private final List<Authors> authorList = new ArrayList<>();
    private final List<Genres> genreList = new ArrayList<>();
    private final List<Books> bookList = new ArrayList<>();

    @ChangeSet(order = "001", id = "addAuthors", author = "kan", runAlways = true)
    public void insertAuthor(MongoTemplate mt) {
        Authors a1 = new Authors("1", "Толстой Лев Николаевич");
        Authors a2 = new Authors("2", "Пушкин Александр Сергеевич");
        authorList.add(mt.save(a1));
        authorList.add(mt.save(a2));
    }

    @ChangeSet(order = "002", id = "addGenres", author = "kan", runAlways = true)
    public void insertGenres(MongoTemplate mt) {
        Genres g1 = new Genres("1", "Роман");
        Genres g2 = new Genres("2", "Сказка");
        genreList.add(mt.save(g1));
        genreList.add(mt.save(g2));
    }

    @ChangeSet(order = "003", id = "addBooks", author = "kan", runAlways = true)
    public void insertBook(MongoTemplate mt) {
        Books b1 = new Books("1", "Война и мир", authorList.get(0), genreList.get(0));
        Books b2 = new Books("2", "У лукоморья дуб зелёный", authorList.get(1), genreList.get(1));
        bookList.add(mt.save(b1));
        bookList.add(mt.save(b2));
    }

}
