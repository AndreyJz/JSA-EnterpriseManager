package com.enterprisemanager.backend.infrastructure.repositories.branch;

import com.enterprisemanager.backend.domain.entities.Branch;
import org.springframework.data.repository.CrudRepository;

public interface BranchRepository extends CrudRepository<Branch, Long> {
}
