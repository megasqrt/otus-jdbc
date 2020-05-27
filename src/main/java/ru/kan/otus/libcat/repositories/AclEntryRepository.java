package ru.kan.otus.libcat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kan.otus.libcat.domain.AclEntry;

@Repository
public interface AclEntryRepository extends JpaRepository<AclEntry, Long> {
}
