package com.enterprisemanager.backend.infrastructure.repositories.personsupply;

import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.PersonSupply;
import com.enterprisemanager.backend.domain.entities.PersonSupplyId;

public interface PersonSupplyRepository extends CrudRepository<PersonSupply, PersonSupplyId>{

}
