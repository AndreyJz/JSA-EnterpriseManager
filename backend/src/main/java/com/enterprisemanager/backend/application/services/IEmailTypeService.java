package com.enterprisemanager.backend.application.services;

import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.domain.entities.EmailType;

public interface IEmailTypeService {
    EmailType save(EmailType emailType);
    Optional<EmailType> findById(Long id);
    List<EmailType> findAll();
    Optional<EmailType> update(Long id, EmailType emailType);
    Optional<EmailType> delete(Long id);
}
