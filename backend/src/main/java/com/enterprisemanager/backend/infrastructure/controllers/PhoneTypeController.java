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

import com.enterprisemanager.backend.application.services.IPhoneTypeService;
import com.enterprisemanager.backend.domain.entities.PhoneType;


@RestController
@RequestMapping("/phoneType")
public class PhoneTypeController {
    @Autowired
    private IPhoneTypeService phoneTypeService;

    @GetMapping
    public List<PhoneType> list() {
        return phoneTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<PhoneType> phoneTypeOptional = phoneTypeService.findById(id);
        if(phoneTypeOptional.isPresent()){
            return ResponseEntity.ok(phoneTypeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create (@RequestBody PhoneType phoneType){
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneTypeService.save(phoneType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody PhoneType phoneType, @PathVariable Long id) {
        Optional<PhoneType> phoneTypeOptional = phoneTypeService.update(id, phoneType);
        if (phoneTypeOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(phoneTypeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<PhoneType> phoneTypeOptional = phoneTypeService.delete(id);
        if (phoneTypeOptional.isPresent()) {
            return ResponseEntity.ok(phoneTypeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}