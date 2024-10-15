package com.enterprisemanager.backend.infrastructure.controllers;

import com.enterprisemanager.backend.application.services.IServiceBranchService;
import com.enterprisemanager.backend.domain.dtos.ServiceBranchPk;
import com.enterprisemanager.backend.domain.entities.ServiceBranch;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/serviceBranch")
public class ServiceBranchController {
    @Autowired
    private IServiceBranchService serviceBranchService;

    @GetMapping
    public List<ServiceBranch> getAll() { return serviceBranchService.findAll(); }

    @GetMapping("/{serviceId}/{branchId}")
    public ResponseEntity<?> getById(@PathVariable Long serviceId, @PathVariable Long branchId) {
        ServiceBranchPk serviceBranchPk = new ServiceBranchPk(serviceId, branchId);
        Optional<ServiceBranch> serviceBranchOptional = serviceBranchService.findById(serviceBranchPk);
        if (serviceBranchOptional.isPresent()) {
            return ResponseEntity.ok(serviceBranchOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ServiceBranch serviceBranch, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceBranchService.save(serviceBranch));
    }

    @PutMapping("/{serviceId}/{branchId}")
    public ResponseEntity<?> update(@PathVariable Long serviceId, @PathVariable Long branchId, @Valid @RequestBody ServiceBranch serviceBranch, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        ServiceBranchPk serviceBranchPk = new ServiceBranchPk(serviceId, branchId);
        Optional<ServiceBranch> serviceBranchOptional = serviceBranchService.findById(serviceBranchPk);
        if (serviceBranchOptional.isPresent()) {
            serviceBranch.setId(serviceBranchPk);
//            serviceBranch.setName(serviceBranchOptional.get().getName());
            return ResponseEntity.ok(serviceBranchService.update(serviceBranchPk,serviceBranch));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{serviceId}/{branchId}")
    public ResponseEntity<?> delete(@PathVariable Long serviceId, @PathVariable Long branchId) {
        ServiceBranchPk serviceBranchPk = new ServiceBranchPk(serviceId, branchId);
        Optional<ServiceBranch> serviceBranchOptional = serviceBranchService.findById(serviceBranchPk);
        if (serviceBranchOptional.isPresent()) {
            serviceBranchService.delete(serviceBranchPk);
            return ResponseEntity.ok(serviceBranchOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(),
                    err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
