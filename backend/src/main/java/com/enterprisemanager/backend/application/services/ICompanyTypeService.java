package com.enterprisemanager.backend.application.services;

import com.enterprisemanager.backend.domain.entities.CompanyType;

import java.util.List;
import java.util.Optional;

public interface ICompanyTypeService {
    CompanyType save(CompanyType country);
    Optional<CompanyType> findById(Long id);
    List<CompanyType> findAll();
    CompanyType update(Long id, CompanyType country);
    void delete(Long id);
}
