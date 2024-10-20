package com.enterprisemanager.backend.infrastructure.repositories.workorderdetail;

import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.WorkOrderDetail;

public interface WorkOrderDetailRepository extends CrudRepository<WorkOrderDetail, Long>{

}
