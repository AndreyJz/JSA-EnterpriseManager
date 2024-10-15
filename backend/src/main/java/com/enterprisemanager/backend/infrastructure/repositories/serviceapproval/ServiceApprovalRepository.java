package com.enterprisemanager.backend.infrastructure.repositories.serviceapproval;

import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.ServiceApproval;

public interface ServiceApprovalRepository extends CrudRepository<ServiceApproval, Long>{

}
