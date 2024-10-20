package com.enterprisemanager.backend.infrastructure.repositories.workorderdetailstatus;

import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.WorkOrderDetailStatus;

public interface WorkOrderDetailStatusRepository extends CrudRepository<WorkOrderDetailStatus, Long>{

}
