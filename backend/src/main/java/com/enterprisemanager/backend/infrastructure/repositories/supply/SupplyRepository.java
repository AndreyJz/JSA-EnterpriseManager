package com.enterprisemanager.backend.infrastructure.repositories.supply;

import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.Supply;

public interface SupplyRepository extends CrudRepository<Supply, Long>{

}
