package com.enterprisemanager.backend.application.services;

import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.domain.entities.WorkOrderDetailStatus;

public interface IWorkOrderDetailStatusService {
    WorkOrderDetailStatus save(WorkOrderDetailStatus workOrderDetailStatus);
    Optional<WorkOrderDetailStatus> findById(Long id);
    List<WorkOrderDetailStatus> findAll();
    Optional<WorkOrderDetailStatus> update(Long id, WorkOrderDetailStatus workOrderDetailStatus);
    Optional<WorkOrderDetailStatus> delete(Long id);
}
