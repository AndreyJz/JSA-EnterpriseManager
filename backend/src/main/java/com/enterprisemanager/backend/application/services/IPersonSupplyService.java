package com.enterprisemanager.backend.application.services;

import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.domain.entities.PersonSupply;
import com.enterprisemanager.backend.domain.entities.PersonSupplyId;

public interface IPersonSupplyService {
    List<PersonSupply> findAll();
    Optional<PersonSupply> findById(PersonSupplyId personSupplyId);
    PersonSupply save(PersonSupply personSupply);
    Optional<PersonSupply> update(PersonSupplyId personSupplyId, PersonSupply personSupply);
    Optional<PersonSupply> delete(PersonSupplyId personSupplyId);
}
