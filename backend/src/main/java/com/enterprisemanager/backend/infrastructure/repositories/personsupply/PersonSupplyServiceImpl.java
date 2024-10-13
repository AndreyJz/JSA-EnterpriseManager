package com.enterprisemanager.backend.infrastructure.repositories.personsupply;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprisemanager.backend.application.services.IPersonSupplyService;
import com.enterprisemanager.backend.domain.entities.PersonSupply;
import com.enterprisemanager.backend.domain.entities.PersonSupplyId;

@Service
public class PersonSupplyServiceImpl implements IPersonSupplyService{

    @Autowired
    private PersonSupplyRepository personSupplyRepository;

    @Transactional(readOnly = true)
    @Override
    public List<PersonSupply> findAll() {
        return (List<PersonSupply>) personSupplyRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<PersonSupply> findById(PersonSupplyId id) {
        return personSupplyRepository.findById(id);
    }

    @Transactional
    @Override
    public PersonSupply save(PersonSupply personSupply) {
        return personSupplyRepository.save(personSupply);
    }

    @Transactional
    @Override
    public Optional<PersonSupply> update(PersonSupplyId id, PersonSupply personSupply) {
        Optional<PersonSupply> personSupplyOld = personSupplyRepository.findById(id);
        if(personSupplyOld.isPresent()){
            PersonSupply personSupplyDb = personSupplyOld.orElseThrow();
            personSupplyDb.setQuantity(personSupply.getQuantity());
            return Optional.of(personSupplyRepository.save(personSupplyDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<PersonSupply> delete(PersonSupplyId id) {
        Optional<PersonSupply> personSupplyOptional = personSupplyRepository.findById(id);
        personSupplyOptional.ifPresent(personSupplyDb -> {
            personSupplyRepository.delete(personSupplyDb);
        });
        return personSupplyOptional;
    }
}
