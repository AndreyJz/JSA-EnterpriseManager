package com.enterprisemanager.backend.application.services;

import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.domain.entities.Supply;

public interface ISupplyService {
    Supply save(Supply supply);
    Optional<Supply> findById(Long id);
    List<Supply> findAll();
    Optional<Supply> update(Long id, Supply supply);
    Optional<Supply> delete(Long id);
}
