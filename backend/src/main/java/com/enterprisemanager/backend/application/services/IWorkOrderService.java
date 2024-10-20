package com.enterprisemanager.backend.application.services;

import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.domain.entities.WorkOrder;

public interface IWorkOrderService {
    WorkOrder save(WorkOrder workOrder);
    Optional<WorkOrder> findById(Long id);
    List<WorkOrder> findAll();
    Optional<WorkOrder> update(Long id, WorkOrder workOrder);
    Optional<WorkOrder> delete(Long id);
}
