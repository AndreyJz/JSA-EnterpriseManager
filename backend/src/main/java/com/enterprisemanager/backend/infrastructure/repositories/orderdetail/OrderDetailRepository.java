package com.enterprisemanager.backend.infrastructure.repositories.orderdetail;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.OrderDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long>{
    @Query("SELECT od FROM OrderDetail od WHERE od.serviceOrder.id = :id")
    List<OrderDetail> findAllByServiceOrderId(@Param("id") Long id);
}
