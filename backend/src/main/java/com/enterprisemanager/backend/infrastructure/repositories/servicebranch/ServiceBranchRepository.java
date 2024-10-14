package com.enterprisemanager.backend.infrastructure.repositories.servicebranch;

import com.enterprisemanager.backend.domain.dtos.ServiceBranchPk;
import com.enterprisemanager.backend.domain.entities.ServiceBranch;
import org.springframework.data.repository.CrudRepository;

public interface ServiceBranchRepository extends CrudRepository<ServiceBranch, ServiceBranchPk> {
}
