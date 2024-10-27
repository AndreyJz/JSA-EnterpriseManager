package com.enterprisemanager.backend.infrastructure.repositories.phone;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enterprisemanager.backend.domain.entities.Phone;


public interface PhoneRepository extends JpaRepository<Phone, Long>{
    @Query("SELECT p FROM Phone p WHERE p.person.id = :id")
    List<Phone> findAllByCustomerId(@Param("id") String id);
}
