package ru.kan.otus.tasker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kan.otus.tasker.domain.AclSid;

import java.util.Optional;

@Repository
public interface AclSidRepository extends JpaRepository<AclSid, Long> {
    Optional<AclSid> findBySid(String sid);
}
