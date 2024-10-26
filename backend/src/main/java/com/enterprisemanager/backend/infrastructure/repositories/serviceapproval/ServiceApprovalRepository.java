package com.enterprisemanager.backend.infrastructure.repositories.serviceapproval;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.ServiceApproval;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceApprovalRepository extends CrudRepository<ServiceApproval, Long>{
    @Query("SELECT sa FROM ServiceApproval sa JOIN WorkOrder wo on wo.id = sa.workOrder.id JOIN ServiceOrder so on so.id = wo.serviceOrder.id WHERE so.employee.id = :id and sa.approvalStatus.name = 'Pending'")
    List<ServiceApproval> findAllByEmployeeId(@Param("id") String id);

    @Query("SELECT sa, od.serviceValue FROM ServiceApproval sa JOIN WorkOrder wo on wo.id = sa.workOrder.id JOIN ServiceOrder so on so.id = wo.serviceOrder.id JOIN OrderDetail od on od.serviceOrder.id = so.id WHERE so.customer.id = :id and sa.approvalStatus.name = 'Pending'")
    List<ServiceApproval> findAllByCustomerId(@Param("id") String id);
}
