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

import com.enterprisemanager.backend.application.services.IEmailService;
import com.enterprisemanager.backend.domain.entities.Email;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private IEmailService emailService;

    @GetMapping
    public List<Email> list() {
        return emailService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Email> emailOptional = emailService.findById(id);
        if(emailOptional.isPresent()){
            return ResponseEntity.ok(emailOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody Email email, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
            }
        return ResponseEntity.status(HttpStatus.CREATED).body(emailService.save(email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Email email, @PathVariable Long id,  BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
            }
        Optional<Email> emailOptional = emailService.update(id, email);
        if (emailOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(emailOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Email> emailOptional = emailService.delete(id);
        if (emailOptional.isPresent()) {
            return ResponseEntity.ok(emailOptional.orElseThrow());
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