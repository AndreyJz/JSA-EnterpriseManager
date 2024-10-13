package com.enterprisemanager.backend.application.services;

import com.enterprisemanager.backend.domain.entities.Region;

import java.util.List;
import java.util.Optional;

public interface IRegionService {
    Region save(Region country);
    Optional<Region> findById(Long id);
    List<Region> findAll();
    Region update(Long id, Region country);
    void delete(Long id);
}
