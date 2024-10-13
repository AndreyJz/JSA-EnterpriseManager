package com.enterprisemanager.backend.infrastructure.repositories.person;

import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.Person;

public interface PersonRepository extends CrudRepository<Person, String>{

}
