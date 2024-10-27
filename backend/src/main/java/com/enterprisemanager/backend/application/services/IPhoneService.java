package com.enterprisemanager.backend.application.services;

import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.domain.entities.Phone;
import com.enterprisemanager.backend.domain.entities.ServiceOrder;

public interface IPhoneService {
    Phone save(Phone phone);
    Optional<Phone> findById(Long id);
    List<Phone> findAll();
    List<Phone> findAllByCustomerId(String id);
    Optional<Phone> update(Long id, Phone phone);
    Optional<Phone> delete(Long id);
}
