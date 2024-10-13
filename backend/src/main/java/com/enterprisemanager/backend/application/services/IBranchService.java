package com.enterprisemanager.backend.application.services;

import com.enterprisemanager.backend.domain.entities.Branch;

import java.util.List;
import java.util.Optional;

public interface IBranchService {
    Branch save(Branch country);
    Optional<Branch> findById(Long id);
    List<Branch> findAll();
    Branch update(Long id, Branch country);
    void delete(Long id);
}
