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

import com.enterprisemanager.backend.application.services.IWorkOrderDetailService;
import com.enterprisemanager.backend.domain.entities.WorkOrderDetail;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/Work_Order_Detail")
public class WorkOrderDetailController {
    @Autowired
    private IWorkOrderDetailService workOrderDetailService;

    @GetMapping
    public List<WorkOrderDetail> list() {
        return workOrderDetailService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<WorkOrderDetail> workOrderDetailOptional = workOrderDetailService.findById(id);
        if(workOrderDetailOptional.isPresent()){
            return ResponseEntity.ok(workOrderDetailOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/Employee_{id}")
    public List<WorkOrderDetail> listByEmployee(@PathVariable String id){
        return workOrderDetailService.findByEmployeeId(id);
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody WorkOrderDetail workOrderDetail, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
            }
        return ResponseEntity.status(HttpStatus.CREATED).body(workOrderDetailService.save(workOrderDetail));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody WorkOrderDetail workOrderDetail, @PathVariable Long id, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<WorkOrderDetail> workOrderDetailOptional = workOrderDetailService.update(id, workOrderDetail);
        if (workOrderDetailOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(workOrderDetailOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<WorkOrderDetail> workOrderDetailOptional = workOrderDetailService.delete(id);
        if (workOrderDetailOptional.isPresent()) {
            return ResponseEntity.ok(workOrderDetailOptional.orElseThrow());
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
