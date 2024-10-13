package com.enterprisemanager.backend.application.services;

import com.enterprisemanager.backend.domain.entities.Company;

import java.util.List;
import java.util.Optional;

public interface ICompanyService {
    Company save(Company country);
    Optional<Company> findById(Long id);
    List<Company> findAll();
    Company update(Long id, Company country);
    void delete(Long id);
}
