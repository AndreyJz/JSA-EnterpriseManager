package com.enterprisemanager.backend.infrastructure.repositories.region;

import com.enterprisemanager.backend.domain.entities.Region;
import org.springframework.data.repository.CrudRepository;

public interface RegionRepository extends CrudRepository<Region, Long> {
}
