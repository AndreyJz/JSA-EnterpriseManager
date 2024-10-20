package com.enterprisemanager.backend.infrastructure.repositories.approvalstatus;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprisemanager.backend.application.services.IApprovalStatusService;
import com.enterprisemanager.backend.domain.entities.ApprovalStatus;


@Service
public class ApprovalStatusImpl implements IApprovalStatusService {

    @Autowired
    private ApprovalStatusRepository approvalStatusRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ApprovalStatus> findAll() {
        return (List<ApprovalStatus>) approvalStatusRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ApprovalStatus> findById(Long id) {
        return approvalStatusRepository.findById(id);
    }

    @Transactional
    @Override
    public ApprovalStatus save(ApprovalStatus approvalStatus) {
        return approvalStatusRepository.save(approvalStatus);
    }

    @Transactional
    @Override
    public Optional<ApprovalStatus> update(Long id, ApprovalStatus approvalStatus) {
        Optional<ApprovalStatus> approvalStatusOld = approvalStatusRepository.findById(id);
        if(approvalStatusOld.isPresent()){
            ApprovalStatus approvalStatusDb = approvalStatusOld.orElseThrow();  
            approvalStatusDb.setName(approvalStatus.getName());
            return Optional.of(approvalStatusRepository.save(approvalStatusDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<ApprovalStatus> delete(Long id) {
        Optional<ApprovalStatus> approvalStatusOptional = approvalStatusRepository.findById(id);
        approvalStatusOptional.ifPresent(approvalStatusDb -> {
            approvalStatusRepository.delete(approvalStatusDb);
        });
        return approvalStatusOptional;
    }
}
