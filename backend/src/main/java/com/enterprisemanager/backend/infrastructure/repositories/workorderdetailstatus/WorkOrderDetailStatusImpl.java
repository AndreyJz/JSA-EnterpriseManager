package com.enterprisemanager.backend.infrastructure.repositories.workorderdetailstatus;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprisemanager.backend.application.services.IWorkOrderDetailStatusService;
import com.enterprisemanager.backend.domain.entities.WorkOrderDetailStatus;


@Service
public class WorkOrderDetailStatusImpl implements IWorkOrderDetailStatusService {

    @Autowired
    private WorkOrderDetailStatusRepository workOrderDetailStatusRepository;

    @Transactional(readOnly = true)
    @Override
    public List<WorkOrderDetailStatus> findAll() {
        return (List<WorkOrderDetailStatus>) workOrderDetailStatusRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<WorkOrderDetailStatus> findById(Long id) {
        return workOrderDetailStatusRepository.findById(id);
    }

    @Transactional
    @Override
    public WorkOrderDetailStatus save(WorkOrderDetailStatus workOrderDetailStatus) {
        return workOrderDetailStatusRepository.save(workOrderDetailStatus);
    }

    @Transactional
    @Override
    public Optional<WorkOrderDetailStatus> update(Long id, WorkOrderDetailStatus workOrderDetailStatus) {
        Optional<WorkOrderDetailStatus> workOrderDetailStatusOld = workOrderDetailStatusRepository.findById(id);
        if(workOrderDetailStatusOld.isPresent()){
            WorkOrderDetailStatus workOrderDetailStatusDb = workOrderDetailStatusOld.orElseThrow();  
            workOrderDetailStatusDb.setName(workOrderDetailStatus.getName());
            return Optional.of(workOrderDetailStatusRepository.save(workOrderDetailStatusDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<WorkOrderDetailStatus> delete(Long id) {
        Optional<WorkOrderDetailStatus> workOrderDetailStatusOptional = workOrderDetailStatusRepository.findById(id);
        workOrderDetailStatusOptional.ifPresent(workOrderDetailStatusDb -> {
            workOrderDetailStatusRepository.delete(workOrderDetailStatusDb);
        });
        return workOrderDetailStatusOptional;
    }
}
