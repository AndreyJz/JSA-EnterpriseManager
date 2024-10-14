package com.enterprisemanager.backend.application.services;

import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.domain.entities.ServiceApproval;

public interface IServiceApprovalService {
    ServiceApproval save(ServiceApproval serviceApproval);
    Optional<ServiceApproval> findById(Long id);
    List<ServiceApproval> findAll();
    Optional<ServiceApproval> update(Long id, ServiceApproval serviceApproval);
    Optional<ServiceApproval> delete(Long id);
}
