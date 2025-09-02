package org.nira.todo.repository;

import org.nira.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long>, JpaSpecificationExecutor<Todo> {
    Page<Todo> findAll (Pageable pageable);
}
