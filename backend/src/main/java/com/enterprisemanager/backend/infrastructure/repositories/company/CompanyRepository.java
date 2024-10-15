package com.enterprisemanager.backend.infrastructure.repositories.company;

import com.enterprisemanager.backend.domain.entities.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
}
