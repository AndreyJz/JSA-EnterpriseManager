package com.enterprisemanager.backend.infrastructure.repositories.emailtype;

import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.EmailType;

public interface EmailTypeRepository extends CrudRepository<EmailType, Long>{

}
