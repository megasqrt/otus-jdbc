package ru.kan.otus.libcat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kan.otus.libcat.domain.Comments;

public interface CommentRepositoryJpa extends JpaRepository<Comments, Long> {
}
