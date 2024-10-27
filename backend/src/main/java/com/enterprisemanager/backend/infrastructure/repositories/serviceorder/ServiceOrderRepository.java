package com.enterprisemanager.backend.infrastructure.repositories.serviceorder;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.ServiceOrder;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceOrderRepository extends CrudRepository<ServiceOrder, Long>{
    @Query("SELECT so FROM ServiceOrder so WHERE so.employee.id = :id")
    List<ServiceOrder> findAllByEmployeeId(@Param("id") String id);

    @Query("SELECT so FROM ServiceOrder so WHERE so.customer.id = :id")
    List<ServiceOrder> findAllByCustomerId(@Param("id") String id);
}
