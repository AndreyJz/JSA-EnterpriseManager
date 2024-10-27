package com.enterprisemanager.backend.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.enterprisemanager.backend.application.services.IPhoneService;
import com.enterprisemanager.backend.domain.entities.Phone;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/Phone")
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

    @GetMapping("/person/{id}")
    public List<Phone> list(@PathVariable String id){
        return phoneService.findAllByCustomerId(id);
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody Phone phone, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
            }
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneService.save(phone));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Phone phone, @PathVariable Long id, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
            }
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

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
        errors.put(err.getField(), "El campo " + err.getField() + " " +

        err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
        }
}
