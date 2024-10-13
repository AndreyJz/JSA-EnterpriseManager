package com.enterprisemanager.backend.infrastructure.repositories.persontype;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprisemanager.backend.application.services.IPersonTypeService;
import com.enterprisemanager.backend.domain.entities.PersonType;

@Service
public class PersonTypeServiceImpl implements IPersonTypeService{

    @Autowired
    private PersonTypeRepository personTypeRepository;

    @Transactional(readOnly = true)
    @Override
    public List<PersonType> findAll() {
        return (List<PersonType>) personTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<PersonType> findById(Long id) {
        return personTypeRepository.findById(id);
    }

    @Transactional
    @Override
    public PersonType save(PersonType personType) {
        return personTypeRepository.save(personType);
    }

    @Transactional
    @Override
    public Optional<PersonType> update(Long id, PersonType personType) {
        Optional<PersonType> personTypeOld = personTypeRepository.findById(id);
        if(personTypeOld.isPresent()){
            PersonType personTypeDb = personTypeOld.orElseThrow();  
            personTypeDb.setName(personType.getName());
            return Optional.of(personTypeRepository.save(personTypeDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<PersonType> delete(Long id) {
        Optional<PersonType> personTypeOptional = personTypeRepository.findById(id);
        personTypeOptional.ifPresent(personTypeDb -> {
            personTypeRepository.delete(personTypeDb);
        });
        return personTypeOptional;
    }
}
