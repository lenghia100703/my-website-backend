package org.web.mywebsite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.web.mywebsite.entities.YearEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface YearRepository extends JpaRepository<YearEntity, Long> {
    Optional<YearEntity> findFirstByOrderByEndDateDesc();

    @Query("SELECT s FROM YearEntity s WHERE :currentDate BETWEEN s.startDate AND s.endDate")
    Optional<YearEntity> findCurrentYear(@Param("currentDate") LocalDate currentDate);
}
