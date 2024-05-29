package org.web.mywebsite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.web.mywebsite.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByEmail(String email);

    @Query("SELECT t FROM UserEntity t WHERE t.role <> org.web.mywebsite.enums.Role.ADMIN")
    List<UserEntity> findAllUserByRole();
}
