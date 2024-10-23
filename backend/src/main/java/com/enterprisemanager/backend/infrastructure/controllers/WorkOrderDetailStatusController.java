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

import com.enterprisemanager.backend.application.services.IWorkOrderDetailStatusService;
import com.enterprisemanager.backend.domain.entities.WorkOrderDetailStatus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/Work Detail Status")
public class WorkOrderDetailStatusController {
    @Autowired
    private IWorkOrderDetailStatusService workOrderDetailStatusService;

    @GetMapping
    public List<WorkOrderDetailStatus> list() {
        return workOrderDetailStatusService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<WorkOrderDetailStatus> workOrderDetailStatusOptional = workOrderDetailStatusService.findById(id);
        if(workOrderDetailStatusOptional.isPresent()){
            return ResponseEntity.ok(workOrderDetailStatusOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody WorkOrderDetailStatus workOrderDetailStatus, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
            }
        return ResponseEntity.status(HttpStatus.CREATED).body(workOrderDetailStatusService.save(workOrderDetailStatus));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody WorkOrderDetailStatus workOrderDetailStatus, @PathVariable Long id, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<WorkOrderDetailStatus> workOrderDetailStatusOptional = workOrderDetailStatusService.update(id, workOrderDetailStatus);
        if (workOrderDetailStatusOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(workOrderDetailStatusOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<WorkOrderDetailStatus> workOrderDetailStatusOptional = workOrderDetailStatusService.delete(id);
        if (workOrderDetailStatusOptional.isPresent()) {
            return ResponseEntity.ok(workOrderDetailStatusOptional.orElseThrow());
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
