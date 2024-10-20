package com.enterprisemanager.backend.infrastructure.repositories.orderdetail;

import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.OrderDetail;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long>{

}
