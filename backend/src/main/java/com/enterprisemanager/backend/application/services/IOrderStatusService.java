package com.enterprisemanager.backend.application.services;

import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.domain.entities.OrderStatus;

public interface IOrderStatusService {
    OrderStatus save(OrderStatus orderStatus);
    Optional<OrderStatus> findById(Long id);
    List<OrderStatus> findAll();
    Optional<OrderStatus> update(Long id, OrderStatus orderStatus);
    Optional<OrderStatus> delete(Long id);
}
