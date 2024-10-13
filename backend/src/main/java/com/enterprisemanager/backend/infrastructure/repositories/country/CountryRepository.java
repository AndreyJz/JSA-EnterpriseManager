package com.enterprisemanager.backend.infrastructure.repositories.country;

import com.enterprisemanager.backend.domain.entities.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
