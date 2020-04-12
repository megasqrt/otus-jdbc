package ru.kan.otus.libcat.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.kan.otus.libcat.domain.Authors;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorsMapper implements RowMapper<Authors> {
    @Override
    public Authors mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        String fullName = resultSet.getString("fullName");
        return new Authors(id, fullName);
    }
}
