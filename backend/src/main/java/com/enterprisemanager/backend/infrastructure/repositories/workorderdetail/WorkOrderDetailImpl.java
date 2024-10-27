package com.enterprisemanager.backend.infrastructure.repositories.workorderdetail;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprisemanager.backend.application.services.IWorkOrderDetailService;
import com.enterprisemanager.backend.domain.entities.WorkOrderDetail;
import java.time.LocalDateTime;



@Service
public class WorkOrderDetailImpl implements IWorkOrderDetailService {

    @Autowired
    private WorkOrderDetailRepository workOrderDetailRepository;

    @Transactional(readOnly = true)
    @Override
    public List<WorkOrderDetail> findAll() {
        return (List<WorkOrderDetail>) workOrderDetailRepository.findAll();
    }

    @Override
    public List<WorkOrderDetail> findByEmployeeId(String id) {
        return (List<WorkOrderDetail>) workOrderDetailRepository.findByEmployeeId(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<WorkOrderDetail> findById(Long id) {
        return workOrderDetailRepository.findById(id);
    }

    @Transactional
    @Override
    public WorkOrderDetail save(WorkOrderDetail workOrderDetail) {
        workOrderDetail.setDate(LocalDateTime.now());
        return workOrderDetailRepository.save(workOrderDetail);
    }

    @Transactional
    @Override
    public Optional<WorkOrderDetail> update(Long id, WorkOrderDetail workOrderDetail) {
        Optional<WorkOrderDetail> workOrderDetailOld = workOrderDetailRepository.findById(id);
        if(workOrderDetailOld.isPresent()){
            WorkOrderDetail workOrderDetailDb = workOrderDetailOld.orElseThrow();  

            workOrderDetailDb.setWorkOrderDetailStatus(workOrderDetail.getWorkOrderDetailStatus());
            workOrderDetailDb.setServiceBranch(workOrderDetail.getServiceBranch());
            workOrderDetailDb.setWorkOrder(workOrderDetail.getWorkOrder());
            workOrderDetailDb.setPerson(workOrderDetail.getPerson());

            return Optional.of(workOrderDetailRepository.save(workOrderDetailDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<WorkOrderDetail> delete(Long id) {
        Optional<WorkOrderDetail> workOrderDetailOptional = workOrderDetailRepository.findById(id);
        workOrderDetailOptional.ifPresent(workOrderDetailDb -> {
            workOrderDetailRepository.delete(workOrderDetailDb);
        });
        return workOrderDetailOptional;
    }
}
