package org.web.mywebsite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.mywebsite.entities.SemesterEntity;

import java.util.Optional;

public interface SemesterRepository extends JpaRepository<SemesterEntity, Long> {
    Optional<SemesterEntity> findFirstByOrderByEndDateDesc();
}
