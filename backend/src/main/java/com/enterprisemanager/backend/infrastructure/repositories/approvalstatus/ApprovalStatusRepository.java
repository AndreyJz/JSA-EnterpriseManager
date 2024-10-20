package com.enterprisemanager.backend.infrastructure.repositories.approvalstatus;

import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.ApprovalStatus;

public interface ApprovalStatusRepository extends CrudRepository<ApprovalStatus, Long>{

}
