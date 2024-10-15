package com.enterprisemanager.backend.infrastructure.repositories.city;

import com.enterprisemanager.backend.domain.entities.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {
}
