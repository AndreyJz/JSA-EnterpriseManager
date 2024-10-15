package com.enterprisemanager.backend.infrastructure.repositories.serviceorder;

import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.ServiceOrder;

public interface ServiceOrderRepository extends CrudRepository<ServiceOrder, Long>{

}
