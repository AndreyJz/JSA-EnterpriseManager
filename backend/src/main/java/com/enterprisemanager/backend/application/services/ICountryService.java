package com.enterprisemanager.backend.application.services;

import com.enterprisemanager.backend.domain.entities.Country;

import java.util.List;
import java.util.Optional;

public interface ICountryService {
    Country save(Country country);
    Optional<Country> findById(Long id);
    List<Country> findAll();
    Country update(Long id, Country country);
    void delete(Long id);
}
