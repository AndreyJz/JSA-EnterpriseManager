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

import com.enterprisemanager.backend.application.services.IServiceApprovalService;
import com.enterprisemanager.backend.domain.entities.ServiceApproval;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/Service_Approval")
public class ServiceApprovalController {
    @Autowired
    private IServiceApprovalService serviceApprovalService;

    @GetMapping
    public List<ServiceApproval> list() {
        return serviceApprovalService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<ServiceApproval> serviceApprovalOptional = serviceApprovalService.findById(id);
        if(serviceApprovalOptional.isPresent()){
            return ResponseEntity.ok(serviceApprovalOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody ServiceApproval serviceApproval, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
            }
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceApprovalService.save(serviceApproval));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ServiceApproval serviceApproval, @PathVariable Long id, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<ServiceApproval> serviceApprovalOptional = serviceApprovalService.update(id, serviceApproval);
        if (serviceApprovalOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceApprovalOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<ServiceApproval> serviceApprovalOptional = serviceApprovalService.delete(id);
        if (serviceApprovalOptional.isPresent()) {
            return ResponseEntity.ok(serviceApprovalOptional.orElseThrow());
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
