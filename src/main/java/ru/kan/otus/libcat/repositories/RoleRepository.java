package ru.kan.otus.libcat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kan.otus.libcat.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
