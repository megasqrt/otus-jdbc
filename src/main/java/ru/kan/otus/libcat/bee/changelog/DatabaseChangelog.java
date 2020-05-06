package ru.kan.otus.libcat.bee.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "addSequens", author = "kan")
    public void initSequens(DB db) {
        DBCollection myCollection = db.getCollection("counters");
        BasicDBObject doc = new BasicDBObject().append("seq", "0");
        myCollection.insert(doc);
    }

    @ChangeSet(order = "002", id = "addBook", author = "kan")
    public void insertBook(DB db) {
        DBCollection myCollection = db.getCollection("BOOKS");
        BasicDBObject doc = new BasicDBObject().append("title", "Test");
        myCollection.insert(doc);
    }
}
