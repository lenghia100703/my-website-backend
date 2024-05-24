package org.web.mywebsite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.mywebsite.entities.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
