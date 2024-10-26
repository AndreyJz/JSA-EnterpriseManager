package com.enterprisemanager.backend.infrastructure.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprisemanager.backend.application.services.IApprovalStatusService;
import com.enterprisemanager.backend.domain.entities.ApprovalStatus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/Approval_Status")
public class ApprovalStatusController {
    @Autowired
    private IApprovalStatusService approvalStatusService;

    @GetMapping
    public List<ApprovalStatus> list() {
        return approvalStatusService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<ApprovalStatus> approvalStatusOptional = approvalStatusService.findById(id);
        if(approvalStatusOptional.isPresent()){
            return ResponseEntity.ok(approvalStatusOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody ApprovalStatus approvalStatus, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
            }
        return ResponseEntity.status(HttpStatus.CREATED).body(approvalStatusService.save(approvalStatus));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ApprovalStatus approvalStatus, @PathVariable Long id, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<ApprovalStatus> approvalStatusOptional = approvalStatusService.update(id, approvalStatus);
        if (approvalStatusOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(approvalStatusOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<ApprovalStatus> approvalStatusOptional = approvalStatusService.delete(id);
        if (approvalStatusOptional.isPresent()) {
            return ResponseEntity.ok(approvalStatusOptional.orElseThrow());
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
