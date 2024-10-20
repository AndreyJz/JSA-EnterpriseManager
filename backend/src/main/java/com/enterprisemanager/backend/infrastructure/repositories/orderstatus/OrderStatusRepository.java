package com.enterprisemanager.backend.infrastructure.repositories.orderstatus;

import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.OrderStatus;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, Long>{

}
