package com.enterprisemanager.backend.infrastructure.repositories.email;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.enterprisemanager.backend.domain.entities.Email;

public interface EmailRepository extends JpaRepository<Email, Long>{
    @Query("SELECT e FROM Email e WHERE e.person.id = :id")
    List<Email> findAllByCustomerId(@Param("id") String person);
}
