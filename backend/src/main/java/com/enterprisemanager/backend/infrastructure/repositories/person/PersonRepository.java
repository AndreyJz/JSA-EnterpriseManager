package com.enterprisemanager.backend.infrastructure.repositories.person;

import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.Person;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, String>{
    boolean existsByUsername(String username);
    Optional<Person> findByUsername(String username);
}
