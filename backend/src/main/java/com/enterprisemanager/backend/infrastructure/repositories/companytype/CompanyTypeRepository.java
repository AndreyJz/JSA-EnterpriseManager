package com.enterprisemanager.backend.infrastructure.repositories.companytype;

import com.enterprisemanager.backend.domain.entities.CompanyType;
import org.springframework.data.repository.CrudRepository;

public interface CompanyTypeRepository extends CrudRepository<CompanyType, Long> {
}
