package com.enterprisemanager.backend.infrastructure.repositories.workorder;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprisemanager.backend.application.services.IWorkOrderService;
import com.enterprisemanager.backend.domain.entities.WorkOrder;
import java.time.LocalDateTime;



@Service
public class WorkOrderImpl implements IWorkOrderService {

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Transactional(readOnly = true)
    @Override
    public List<WorkOrder> findAll() {
        return (List<WorkOrder>) workOrderRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<WorkOrder> findById(Long id) {
        return workOrderRepository.findById(id);
    }

    @Transactional
    @Override
    public WorkOrder save(WorkOrder workOrder) {
        workOrder.setAssignDate(LocalDateTime.now());
        return workOrderRepository.save(workOrder);
    }

    @Transactional
    @Override
    public Optional<WorkOrder> update(Long id, WorkOrder workOrder) {
        Optional<WorkOrder> workOrderOld = workOrderRepository.findById(id);
        if(workOrderOld.isPresent()){
            WorkOrder workOrderDb = workOrderOld.orElseThrow();  
            workOrderDb.setServiceOrder(workOrder.getServiceOrder());
            workOrderDb.setWorkOrderNum(workOrder.getWorkOrderNum());
            return Optional.of(workOrderRepository.save(workOrderDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<WorkOrder> delete(Long id) {
        Optional<WorkOrder> workOrderOptional = workOrderRepository.findById(id);
        workOrderOptional.ifPresent(workOrderDb -> {
            workOrderRepository.delete(workOrderDb);
        });
        return workOrderOptional;
    }
}
