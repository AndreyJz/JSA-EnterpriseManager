package com.enterprisemanager.backend.application.services;

import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.domain.entities.PersonType;

public interface IPersonTypeService {
    PersonType save(PersonType personType);
    Optional<PersonType> findById(Long id);
    List<PersonType> findAll();
    Optional<PersonType> update(Long id, PersonType personType);
    Optional<PersonType> delete(Long id);
}
