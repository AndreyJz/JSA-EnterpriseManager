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

import com.enterprisemanager.backend.application.services.IPersonSupplyService;
import com.enterprisemanager.backend.domain.entities.PersonSupply;
import com.enterprisemanager.backend.domain.entities.PersonSupplyId;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/Person Supply")
public class PersonSupplyController {
    @Autowired
    private IPersonSupplyService personSupplyService;

    @GetMapping
        public List<PersonSupply> list() {
        return personSupplyService.findAll();
    }

    @GetMapping("/{personid}/{supplyid}")

    public ResponseEntity<?> view(@PathVariable String personid, @PathVariable Long supplyid){
        PersonSupplyId personSupplyId = new PersonSupplyId(personid, supplyid);
        Optional<PersonSupply> personSupplyOptional = personSupplyService.findById(personSupplyId);
        if(personSupplyOptional.isPresent()){
            return ResponseEntity.ok(personSupplyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody PersonSupply personSupply, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
            }
        return ResponseEntity.status(HttpStatus.CREATED).body(personSupplyService.save(personSupply));
    }

        
    @PutMapping("/{personid}/{supplyid}")
    public ResponseEntity<?> update(@Valid @RequestBody PersonSupply personSupply,@PathVariable String personid, @PathVariable Long supplyid, BindingResult result) {
        PersonSupplyId personSupplyId = new PersonSupplyId(personid, supplyid);
        if (result.hasFieldErrors()) {
            return validation(result);
            }
        Optional<PersonSupply> personSupplyOptional = personSupplyService.update(personSupplyId, personSupply);
        if (personSupplyOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(personSupplyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{personid}/{supplyid}")
    public ResponseEntity<?> delete(@PathVariable String personid, @PathVariable Long supplyid) {
        PersonSupplyId personSupplyId = new PersonSupplyId(personid, supplyid);
        Optional<PersonSupply> personSupplyOptional = personSupplyService.delete(personSupplyId);
        if (personSupplyOptional.isPresent()) {
            return ResponseEntity.ok(personSupplyOptional.orElseThrow());
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
