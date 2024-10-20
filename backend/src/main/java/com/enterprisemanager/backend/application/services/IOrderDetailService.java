package com.enterprisemanager.backend.application.services;

import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.domain.entities.OrderDetail;

public interface IOrderDetailService {
    OrderDetail save(OrderDetail orderDetail);
    Optional<OrderDetail> findById(Long id);
    List<OrderDetail> findAll();
    Optional<OrderDetail> update(Long id, OrderDetail orderDetail);
    Optional<OrderDetail> delete(Long id);
}
