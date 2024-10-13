package com.enterprisemanager.backend.infrastructure.repositories.phone;

import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.Phone;

public interface PhoneRepository extends CrudRepository<Phone, Long>{

}
