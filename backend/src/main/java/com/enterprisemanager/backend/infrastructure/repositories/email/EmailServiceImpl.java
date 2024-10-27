package com.enterprisemanager.backend.infrastructure.repositories.email;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprisemanager.backend.application.services.IEmailService;
import com.enterprisemanager.backend.domain.entities.Email;


@Service
public class EmailServiceImpl implements IEmailService{

    @Autowired
    private EmailRepository emailRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Email> findAll() {
        return (List<Email>) emailRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Email> findById(Long id) {
        return emailRepository.findById(id);
    }

    @Transactional
    @Override
    public Email save(Email email) {
        return emailRepository.save(email);
    }

    @Transactional
    @Override
    public Optional<Email> update(Long id, Email email) {
        Optional<Email> emailOld = emailRepository.findById(id);
        if(emailOld.isPresent()){
            Email emailDb = emailOld.orElseThrow();
            emailDb.setMail(email.getMail());
            emailDb.setEmailType(email.getEmailType());
            emailDb.setPerson(email.getPerson());
            return Optional.of(emailRepository.save(emailDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Email> delete(Long id) {
        Optional<Email> emailOptional = emailRepository.findById(id);
        emailOptional.ifPresent(emailDb -> {
            emailRepository.delete(emailDb);
        });
        return emailOptional;
    }

    @Override
    public List<Email> findAllByCustomerId(String id) {
        return (List<Email>) emailRepository.findAllByCustomerId(id);
    }
}

