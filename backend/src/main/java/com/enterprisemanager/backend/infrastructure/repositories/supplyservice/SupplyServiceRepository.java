package com.enterprisemanager.backend.infrastructure.repositories.supplyservice;

import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.SupplyService;
import com.enterprisemanager.backend.domain.entities.SupplyServiceId;

public interface SupplyServiceRepository extends CrudRepository<SupplyService, SupplyServiceId>{

}
