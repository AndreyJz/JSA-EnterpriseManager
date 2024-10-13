package com.enterprisemanager.backend.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.enterprisemanager.backend.application.services.IPhoneService;
import com.enterprisemanager.backend.domain.entities.Phone;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/phone")
public class PhoneController {
    @Autowired
    private IPhoneService phoneService;

    @GetMapping
    public List<Phone> list() {
        return phoneService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Phone> phoneOptional = phoneService.findById(id);
        if(phoneOptional.isPresent()){
            return ResponseEntity.ok(phoneOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create (@RequestBody Phone phone){
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneService.save(phone));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Phone phone, @PathVariable Long id) {
        Optional<Phone> phoneOptional = phoneService.update(id, phone);
        if (phoneOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(phoneOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Phone> phoneOptional = phoneService.delete(id);
        if (phoneOptional.isPresent()) {
            return ResponseEntity.ok(phoneOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
