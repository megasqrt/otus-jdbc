package ru.kan.otus.libcat.dao;

import ru.kan.otus.libcat.domain.Genres;

public interface GenresDao {
    Integer getCount();

    int deleteById(Long id);

    Genres getById(Long id);

    Genres getByName(String name);

    String getTitleById(Long id);

    Long insert(Genres genred);
}
