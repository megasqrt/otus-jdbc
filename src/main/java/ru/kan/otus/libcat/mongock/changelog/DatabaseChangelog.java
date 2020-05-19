package ru.kan.otus.libcat.mongock.changelog;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.kan.otus.libcat.domain.Authors;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.domain.Comments;
import ru.kan.otus.libcat.domain.Genres;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    /*@ChangeSet(order = "001", id = "addSequens", author = "kan")
    public void initSequens(DB db) {
        DBCollection myCollection = db.getCollection("counters");
        BasicDBObject doc = new BasicDBObject().append("seq", "0");
        myCollection.insert(doc);
    }*/
    private List<Authors> authorList = new ArrayList<>();
    private List<Genres> genreList = new ArrayList<>();
    private List<Books> bookList = new ArrayList<>();

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
        Books b1 = new Books("1", "Война и мир", authorList.get(0), genreList.get(0), null);
        Books b2 = new Books("2", "У лукоморья дуб зелёный", authorList.get(1), genreList.get(1), null);
        bookList.add(mt.save(b1));
        bookList.add(mt.save(b2));
    }

    @ChangeSet(order = "004", id = "addComments", author = "kan", runAlways = true)
    public void insertComments(MongoTemplate mt) {
        Comments c1 = new Comments("1", "Великолепно", bookList.get(0));
        Comments c2 = new Comments("2", "Шедеврально", bookList.get(1));
        mt.save(c1);
        mt.save(c2);
    }


}
