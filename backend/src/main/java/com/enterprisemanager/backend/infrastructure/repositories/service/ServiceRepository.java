package com.enterprisemanager.backend.infrastructure.repositories.service;

import com.enterprisemanager.backend.domain.entities.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service, Long> {
}
