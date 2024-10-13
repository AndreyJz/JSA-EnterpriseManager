package com.enterprisemanager.backend.application.services;

import com.enterprisemanager.backend.domain.dtos.ServiceBranchPk;
import com.enterprisemanager.backend.domain.entities.ServiceBranch;

import java.util.List;
import java.util.Optional;

public interface IServiceBranchService {
    ServiceBranch save(ServiceBranch country);
    Optional<ServiceBranch> findById(ServiceBranchPk id);
    List<ServiceBranch> findAll();
    ServiceBranch update(ServiceBranchPk id, ServiceBranch country);
    void delete(ServiceBranchPk id);
}
