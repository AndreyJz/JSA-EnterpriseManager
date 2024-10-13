package com.enterprisemanager.backend.infrastructure.repositories.emailtype;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprisemanager.backend.application.services.IEmailTypeService;
import com.enterprisemanager.backend.domain.entities.EmailType;

@Service
public class EmailTypeServiceImpl implements IEmailTypeService{

    @Autowired
    private EmailTypeRepository emailTypeRepository;

    @Transactional(readOnly = true)
    @Override
    public List<EmailType> findAll() {
        return (List<EmailType>) emailTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<EmailType> findById(Long id) {
        return emailTypeRepository.findById(id);
    }

    @Transactional
    @Override
    public EmailType save(EmailType emailType) {
        return emailTypeRepository.save(emailType);
    }

    @Transactional
    @Override
    public Optional<EmailType> update(Long id, EmailType emailType) {
        Optional<EmailType> emailTypeOld = emailTypeRepository.findById(id);
        if(emailTypeOld.isPresent()){
            EmailType emailTypeDb = emailTypeOld.orElseThrow();  
            emailTypeDb.setName(emailType.getName());
            return Optional.of(emailTypeRepository.save(emailTypeDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<EmailType> delete(Long id) {
        Optional<EmailType> emailTypeOptional = emailTypeRepository.findById(id);
        emailTypeOptional.ifPresent(emailTypeDb -> {
            emailTypeRepository.delete(emailTypeDb);
        });
        return emailTypeOptional;
    }
}
