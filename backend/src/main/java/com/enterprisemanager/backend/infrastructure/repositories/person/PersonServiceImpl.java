package com.enterprisemanager.backend.infrastructure.repositories.person;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.application.services.IRoleService;
import com.enterprisemanager.backend.domain.entities.Role;
//import com.enterprisemanager.backend.infrastructure.repositories.role.RoleRepository;
import com.enterprisemanager.backend.infrastructure.utils.exceptions.InvalidPasswordException;
import com.enterprisemanager.backend.infrastructure.utils.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprisemanager.backend.application.services.IPersonService;
import com.enterprisemanager.backend.domain.entities.Person;
import org.springframework.util.StringUtils;

@Service
public class PersonServiceImpl implements IPersonService{

    @Autowired
    private PersonRepository personRepository;

//    @Autowired
//    private RoleRepository roleRepository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
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
    public Person save(Person newUser) {
        validatePassword(newUser);

        Person user = new Person();
        user.setId(newUser.getId());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setUsername(newUser.getUsername());
        user.setName(newUser.getName());
        user.setLastname(newUser.getLastname());
        user.setDate(LocalDateTime.now());
        user.setPersonType(newUser.getPersonType());
        user.setBranch(newUser.getBranch());
        Role defualtRole = roleService.findDefaultRole()
                .orElseThrow(() -> new ObjectNotFoundException("Default role not found"));
        user.setRole(defualtRole);
        return personRepository.save(user);
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
            personDb.setRole(person.getRole());
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

    @Override
    public boolean existsByUsername(String username) {
        return personRepository.existsByUsername(username);
    }

    @Override
    public Person registrOneCustomer(Person newUser) {
        validatePassword(newUser);

        Person user = new Person();
        user.setId(newUser.getId());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setUsername(newUser.getUsername());
        user.setName(newUser.getName());
        user.setDate(LocalDateTime.now());
        user.setLastname(newUser.getLastname());
        user.setPersonType(newUser.getPersonType());
        user.setBranch(newUser.getBranch());
        Role defualtRole = roleService.findDefaultRole()
                        .orElseThrow(() -> new ObjectNotFoundException("Default role not found"));
        user.setRole(defualtRole);

        return personRepository.save(user);
    }

    @Override
    public Optional<Person> findOneByUsername(String username) {
        return personRepository.findByUsername(username);
    }
    private void validatePassword(Person dto) {

        if(!StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getRepeatedPassword())){
            throw new InvalidPasswordException("Passwords don't match %s and %s" + dto.getRepeatedPassword() + dto.getPassword());
        }

        if(!dto.getPassword().equals(dto.getRepeatedPassword())){
            throw new InvalidPasswordException("Passwords don't match %s and %s" + dto.getRepeatedPassword() + dto.getPassword());
        }

    }
}
