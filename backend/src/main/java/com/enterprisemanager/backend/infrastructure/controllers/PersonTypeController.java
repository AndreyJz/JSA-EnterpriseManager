package com.enterprisemanager.backend.infrastructure.controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprisemanager.backend.application.services.IPersonTypeService;
import com.enterprisemanager.backend.domain.entities.PersonType;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/personType")
public class PersonTypeController {
    @Autowired
    private IPersonTypeService personTypeService;

    @GetMapping
    public List<PersonType> list() {
        return personTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<PersonType> personTypeOptional = personTypeService.findById(id);
        if(personTypeOptional.isPresent()){
            return ResponseEntity.ok(personTypeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody PersonType personType, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
            }
        return ResponseEntity.status(HttpStatus.CREATED).body(personTypeService.save(personType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody PersonType personType, @PathVariable Long id,  BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
            }
        Optional<PersonType> personTypeOptional = personTypeService.update(id, personType);
        if (personTypeOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(personTypeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<PersonType> personTypeOptional = personTypeService.delete(id);
        if (personTypeOptional.isPresent()) {
            return ResponseEntity.ok(personTypeOptional.orElseThrow());
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