package org.web.mywebsite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.mywebsite.entities.YearEntity;

import java.util.Optional;

public interface YearRepository extends JpaRepository<YearEntity, Long> {
    Optional<YearEntity> findFirstByOrderByEndDateDesc();
}
