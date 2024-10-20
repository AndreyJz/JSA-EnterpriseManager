package com.enterprisemanager.backend.application.services;

import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.domain.entities.ApprovalStatus;

public interface IApprovalStatusService {
    ApprovalStatus save(ApprovalStatus approvalStatus);
    Optional<ApprovalStatus> findById(Long id);
    List<ApprovalStatus> findAll();
    Optional<ApprovalStatus> update(Long id, ApprovalStatus approvalStatus);
    Optional<ApprovalStatus> delete(Long id);
}
