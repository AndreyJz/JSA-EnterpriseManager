package com.enterprisemanager.backend.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.enterprisemanager.backend.application.services.IPersonService;
import com.enterprisemanager.backend.domain.entities.Person;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private IPersonService personService;

    @GetMapping
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
    public ResponseEntity<?> create (@RequestBody Person person){
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Person person, @PathVariable String id) {
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
}