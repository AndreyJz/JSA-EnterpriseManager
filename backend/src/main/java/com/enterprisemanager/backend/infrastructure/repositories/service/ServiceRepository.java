package com.enterprisemanager.backend.infrastructure.repositories.service;

import com.enterprisemanager.backend.domain.entities.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRepository extends CrudRepository<Service, Long> {
    @Query("SELECT s FROM Service s LEFT JOIN ServiceBranch sb on sb.id.serviceId = s.id WHERE sb.id.branchId = null")
    List<Service> findWithoutBranch();
}
