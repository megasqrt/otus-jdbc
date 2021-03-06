package ru.kan.otus.libcat.dao;

import ru.kan.otus.libcat.domain.Authors;

public interface AuthorsDao {
    Integer getCount();

    int deleteById(Long id);

    Authors getById(Long id);

    Authors getByName(String name);

    String getNameById(Long id);

    Long insert(Authors authors);
}
