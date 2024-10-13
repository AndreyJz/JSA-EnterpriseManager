package com.enterprisemanager.backend.application.services;

import java.util.List;
import java.util.Optional;
import com.enterprisemanager.backend.domain.entities.PhoneType;

public interface IPhoneTypeService {
    PhoneType save(PhoneType phoneType);
    Optional<PhoneType> findById(Long id);
    List<PhoneType> findAll();
    Optional<PhoneType> update(Long id, PhoneType phoneType);
    Optional<PhoneType> delete(Long id);
}
