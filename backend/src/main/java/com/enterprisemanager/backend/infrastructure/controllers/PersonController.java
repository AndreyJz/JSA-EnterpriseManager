package com.enterprisemanager.backend.infrastructure.controllers;

import com.enterprisemanager.backend.application.services.auth.AuthenticationService;
import com.enterprisemanager.backend.domain.dtos.RegisterUser;
import com.enterprisemanager.backend.domain.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.enterprisemanager.backend.application.services.IPersonService;
import com.enterprisemanager.backend.domain.entities.Person;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("api/Person")
public class PersonController {
    @Autowired
    private IPersonService personService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public List<Person> list() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable String id){
        Optional<Person> personOptional = personService.findById(id);
        if(personOptional.isPresent()){
            return ResponseEntity.ok(personOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Person person, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        RegisterUser registeredUser = authenticationService.registerOneCustomer(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(person));
    }

    @PostMapping("/customer")
    public ResponseEntity<?> registerOne(@RequestBody @Valid Person newUser, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        RegisterUser registeredUser = authenticationService.registerOneCustomer(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

//    @PostMapping("/register")
//    public ResponseEntity<?> register(@Valid @RequestBody Person user, BindingResult result) {
//        user.setAdmin(false);
//        return create(user, result);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Person person, @PathVariable String id, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
            }
        Optional<Person> personOptional = personService.update(id, person);
        if (personOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(personOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<Person> personOptional = personService.delete(id);
        if (personOptional.isPresent()) {
            return ResponseEntity.ok(personOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
        errors.put(err.getField(), "El campo " + err.getField() + " " +

        err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
        }
}