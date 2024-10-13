package com.enterprisemanager.backend.infrastructure.repositories.email;

import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.Email;

public interface EmailRepository extends CrudRepository<Email, Long>{

}
