package com.enterprisemanager.backend.application.services;

import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.domain.entities.WorkOrderDetail;

public interface IWorkOrderDetailService {
    WorkOrderDetail save(WorkOrderDetail workOrderDetail);
    Optional<WorkOrderDetail> findById(Long id);
    List<WorkOrderDetail> findAll();
    Optional<WorkOrderDetail> update(Long id, WorkOrderDetail workOrderDetail);
    Optional<WorkOrderDetail> delete(Long id);
}
