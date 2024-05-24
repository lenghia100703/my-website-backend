package org.web.mywebsite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.mywebsite.entities.DocumentEntity;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
}
