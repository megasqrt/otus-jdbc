package ru.kan.otus.tasker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kan.otus.tasker.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
