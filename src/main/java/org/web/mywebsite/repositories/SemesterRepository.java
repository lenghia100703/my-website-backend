package org.web.mywebsite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.web.mywebsite.entities.SemesterEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface SemesterRepository extends JpaRepository<SemesterEntity, Long> {
    Optional<SemesterEntity> findFirstByOrderByEndDateDesc();

    @Query("SELECT s FROM SemesterEntity s WHERE :currentDate BETWEEN s.startDate AND s.endDate")
    Optional<SemesterEntity> findCurrentSemester(@Param("currentDate") LocalDate currentDate);
}
