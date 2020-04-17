package ru.kan.otus.libcat.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.kan.otus.libcat.domain.Genres;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenresMapper implements RowMapper<Genres> {
    @Override
    public Genres mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        String title = resultSet.getString("title");
        return new Genres(id, title);
    }
}
