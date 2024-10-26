package com.enterprisemanager.backend.infrastructure.repositories.workorderdetail;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.WorkOrderDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkOrderDetailRepository extends CrudRepository<WorkOrderDetail, Long>{

    @Query("SELECT wo FROM WorkOrderDetail wo WHERE wo.person.id = :id")
    List<WorkOrderDetail> findByEmployeeId(@Param("id") String id);
}
