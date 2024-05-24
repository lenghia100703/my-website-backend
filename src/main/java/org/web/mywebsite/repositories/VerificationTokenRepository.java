package org.web.mywebsite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.mywebsite.entities.VerificationTokenEntity;

public interface VerificationTokenRepository extends JpaRepository<VerificationTokenEntity, Long> {
}
