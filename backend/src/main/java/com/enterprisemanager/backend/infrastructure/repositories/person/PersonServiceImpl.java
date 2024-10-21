package com.enterprisemanager.backend.infrastructure.repositories.person;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.domain.dtos.UserDto;
import com.enterprisemanager.backend.infrastructure.utils.enums.Role;
//import com.enterprisemanager.backend.infrastructure.repositories.role.RoleRepository;
import com.enterprisemanager.backend.infrastructure.utils.exceptions.InvalidPasswordException;
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
    public Person save(Person person) {
//        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
//        List<Role> roles = new ArrayList<>();
//        if (person.isAdmin()) {
//            Optional<Role> optionalRoleAdmin =
//                    roleRepository.findByName("ROLE_ADMIN");
//            optionalRoleAdmin.ifPresent(roles::add);
//        } else {
//            optionalRoleUser.ifPresent(roles::add);
//        }
//        person.setRoles(roles);
//        person.setPassword(passwordEncoder.encode(person.getPassword()));
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

    @Override
    public boolean existsByUsername(String username) {
        return personRepository.existsByUsername(username);
    }

    @Override
    public Person registrOneCustomer(UserDto newUser) {
        validatePassword(newUser);

        Person user = new Person();
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setUsername(newUser.getUsername());
        user.setName(newUser.getName());
        user.setRole(Role.ROLE_CUSTOMER);

        return personRepository.save(user);
    }

    @Override
    public Optional<Person> findOneByUsername(String username) {
        return personRepository.findByUsername(username);
    }
    private void validatePassword(UserDto dto) {

        if(!StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getRepeatedPassword())){
            throw new InvalidPasswordException("Passwords don't match");
        }

        if(!dto.getPassword().equals(dto.getRepeatedPassword())){
            throw new InvalidPasswordException("Passwords don't match");
        }

    }
}
