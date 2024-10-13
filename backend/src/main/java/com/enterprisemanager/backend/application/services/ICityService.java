package com.enterprisemanager.backend.application.services;

import com.enterprisemanager.backend.domain.entities.City;

import java.util.List;
import java.util.Optional;

public interface ICityService {
    City save(City country);
    Optional<City> findById(Long id);
    List<City> findAll();
    City update(Long id, City country);
    void delete(Long id);
}
