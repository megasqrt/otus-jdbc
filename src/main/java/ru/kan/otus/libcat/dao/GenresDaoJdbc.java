package ru.kan.otus.libcat.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.kan.otus.libcat.domain.Genres;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GenresDaoJdbc implements GenresDao {
    private final NamedParameterJdbcOperations jdbc;

    public GenresDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Integer getCount() {
        return null;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public Genres getById(Long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbc.queryForObject("select * from genres where id=:id",
                params, new GenresMapper());

    }

    @Override
    public Genres getByName(String title) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("title", "%" + title + "%");
        List<Genres> genresList = jdbc.query("select * from genres where title like :title",
                params, new GenresMapper());
        if (!genresList.isEmpty())
            return genresList.get(0);
        return null;
    }

    @Override
    public Long insert(Genres genred) {
        return null;
    }
}
