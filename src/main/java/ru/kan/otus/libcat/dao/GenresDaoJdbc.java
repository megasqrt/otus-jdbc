package ru.kan.otus.libcat.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.kan.otus.libcat.domain.Genres;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GenresDaoJdbc implements GenresDao {
    private static final String GENRE_QUERY = "select id,title from genres";
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
        return jdbc.queryForObject(GENRE_QUERY + " where id=:id",
                params, new GenresMapper());

    }

    @Override
    public Genres getByName(String title) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("title", "%" + title + "%");
        List<Genres> genresList = jdbc.query(GENRE_QUERY + " where title like :title",
                params, new GenresMapper());
        if (!genresList.isEmpty())
            return genresList.get(0);
        return null;
    }

    @Override
    public String getTitleById(Long id) {
        return getById(id).getTitle();
    }

    @Override
    public Long insert(Genres genred) {
        return null;
    }
}
