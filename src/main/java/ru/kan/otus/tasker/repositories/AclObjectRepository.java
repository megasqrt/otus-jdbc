package ru.kan.otus.tasker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kan.otus.tasker.domain.AclObject;

@Repository
public interface AclObjectRepository extends JpaRepository<AclObject, Long> {
}
