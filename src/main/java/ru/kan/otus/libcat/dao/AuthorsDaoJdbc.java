package ru.kan.otus.libcat.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.kan.otus.libcat.domain.Authors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class AuthorsDaoJdbc implements AuthorsDao {

    private static final String AUTHORS_QUERY = "select id,fullname from AUTHORS";
    private final NamedParameterJdbcOperations jdbc;

    public AuthorsDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    public Long insert(Authors authors) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("fullName", authors.getFullName());
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into AUTHORS(fullName) values(:fullName)", params, kh);
        return Objects.requireNonNull(kh.getKey()).longValue();
    }

    public Authors getById(Long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbc.queryForObject(AUTHORS_QUERY + " where id=:id",
                params, new AuthorsMapper());
    }

    public String getNameById(Long id) {
        return getById(id).getFullName();
    }

    public Authors getByName(String name) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("name", "%" + name + "%");

        List<Authors> authorsList = jdbc.query(AUTHORS_QUERY + " where fullname like :name",
                params, new AuthorsMapper());
        if (!authorsList.isEmpty())
            return authorsList.get(0);
        return null;
    }

    public int deleteById(Long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbc.update("delete from AUTHORS where id=:id",
                params);
    }

    public Integer getCount() {
        return getAll().size();
    }

    public List<Authors> getAll() {
        return jdbc.query(AUTHORS_QUERY, new AuthorsMapper());
    }
}
