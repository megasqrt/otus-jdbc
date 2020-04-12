package ru.kan.otus.libcat.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.kan.otus.libcat.domain.Books;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BooksMapper implements RowMapper<Books> {
    @Override
    public Books mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        String title = resultSet.getString("title");
        Long author = resultSet.getLong("author");
        Long genre = resultSet.getLong("genre");
        return new Books(id, title, author, genre);
    }
}
