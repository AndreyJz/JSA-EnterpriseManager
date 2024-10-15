package com.enterprisemanager.backend.infrastructure.repositories.person;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprisemanager.backend.application.services.IPersonService;
import com.enterprisemanager.backend.domain.entities.Person;

@Service
public class PersonServiceImpl implements IPersonService{

    @Autowired
    private PersonRepository personRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Person> findAll() {
        return (List<Person>) personRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Person> findById(String id) {
        return personRepository.findById(id);
    }

    @Transactional
    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Transactional
    @Override
    public Optional<Person> update(String id, Person person) {
        Optional<Person> personOld = personRepository.findById(id);
        if(personOld.isPresent()){
            Person personDb = personOld.orElseThrow();
            personDb.setName(person.getName());
            personDb.setLastname(person.getLastname());
            personDb.setDate(person.getDate());
            personDb.setPassword(person.getPassword());
            personDb.setPersonType(person.getPersonType());
            personDb.setBranch(person.getBranch());
            return Optional.of(personRepository.save(personDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Person> delete(String id) {
        Optional<Person> personOptional = personRepository.findById(id);
        personOptional.ifPresent(personDb -> {
            personRepository.delete(personDb);
        });
        return personOptional;
    }
}
