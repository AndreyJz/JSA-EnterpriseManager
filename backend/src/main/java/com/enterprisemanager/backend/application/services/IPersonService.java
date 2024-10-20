package com.enterprisemanager.backend.application.services;

import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.domain.dtos.UserDto;
import com.enterprisemanager.backend.domain.entities.Person;

public interface IPersonService {
    Person save(Person person);
    Optional<Person> findById(String id);
    List<Person> findAll();
    Optional<Person> update(String id, Person person);
    Optional<Person> delete(String id);
    boolean existsByUsername(String username);
    Person registrOneCustomer(UserDto newUser);
    Optional<Person> findOneByUsername(String username);
}
