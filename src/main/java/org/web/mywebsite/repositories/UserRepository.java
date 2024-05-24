package org.web.mywebsite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.mywebsite.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByEmail(String email);
}
