package com.enterprisemanager.backend.application.services;

import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.domain.entities.SupplyService;
import com.enterprisemanager.backend.domain.entities.SupplyServiceId;

public interface ISupplyServiceService {
    List<SupplyService> findAll();
    Optional<SupplyService> findById(SupplyServiceId supplyServiceId);
    SupplyService save(SupplyService supplyService);
    Optional<SupplyService> update(SupplyServiceId supplyServiceId, SupplyService supplyService);
    Optional<SupplyService> delete(SupplyServiceId supplyServiceId);
}
