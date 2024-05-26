package org.web.mywebsite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.mywebsite.entities.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
