package ru.kan.otus.libcat.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.kan.otus.libcat.domain.Books;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class BooksDaoJdbc implements BooksDao {

    private static final String BOOKS_QUERY = "select id,title,author_id,genre_id from Books";
    private final NamedParameterJdbcOperations jdbc;

    public BooksDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public Integer getCount() {
        return getAll().size();
    }

    public int deleteById(Long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbc.update("delete from Books where id=:id",
                params);
    }


    @Override
    public Books getById(Long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        List<Books> books = jdbc.query(BOOKS_QUERY + " where id=:id",
                params, new BooksMapper());
        if (!books.isEmpty())
            return books.get(0);
        return null;

    }

    @Override
    public Long insert(Books books) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", books.getTitle());
        params.addValue("author_id", books.getAuthorId());
        params.addValue("genre_id", books.getGenreId());
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into books(title,author_id,genre_id) values(:title,:author_id,:genre_id)", params, kh);
        return Objects.requireNonNull(kh.getKey()).longValue();
    }

    @Override
    public Books getByTitle(String title) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("title", "%" + title + "%");
        return jdbc.queryForObject(BOOKS_QUERY + " where title like :title",
                params, new BooksMapper());
    }

    public List<Books> getAll() {
        return jdbc.query(BOOKS_QUERY, new BooksMapper());
    }
}
