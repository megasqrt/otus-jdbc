package ru.kan.otus.libcat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kan.otus.libcat.domain.AclSid;

import java.util.Optional;

@Repository
public interface AclSidRepository extends JpaRepository<AclSid, Long> {

    Optional<AclSid> findBySid(String sid);
}
