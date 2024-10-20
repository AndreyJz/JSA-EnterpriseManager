package com.enterprisemanager.backend.infrastructure.repositories.workorder;

import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.WorkOrder;

public interface WorkOrderRepository extends CrudRepository<WorkOrder, Long>{

}
