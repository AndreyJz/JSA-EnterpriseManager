package com.enterprisemanager.backend.infrastructure.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


@RestController
@RequestMapping("/personType")
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
    public ResponseEntity<?> create (@RequestBody PersonType personType){
        return ResponseEntity.status(HttpStatus.CREATED).body(personTypeService.save(personType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody PersonType personType, @PathVariable Long id) {
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
}