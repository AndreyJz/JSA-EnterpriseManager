package com.enterprisemanager.backend.application.services;

import com.enterprisemanager.backend.domain.entities.Service;

import java.util.List;
import java.util.Optional;

public interface IServiceService {
    Service save(Service country);
    Optional<Service> findById(Long id);
    List<Service> findAll();
    List<Service> findWithoutBranch();
    Service update(Long id, Service country);
    void delete(Long id);
}
