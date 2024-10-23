package com.enterprisemanager.backend.infrastructure.repositories.operation;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.enterprisemanager.backend.domain.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    @Query("SELECT o FROM Operation o where o.permitAll = true")
    List<Operation> findByPublicAccess();
}
