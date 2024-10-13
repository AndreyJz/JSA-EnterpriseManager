package com.enterprisemanager.backend.infrastructure.repositories.persontype;

import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.PersonType;

public interface PersonTypeRepository extends CrudRepository<PersonType, Long>{

}
