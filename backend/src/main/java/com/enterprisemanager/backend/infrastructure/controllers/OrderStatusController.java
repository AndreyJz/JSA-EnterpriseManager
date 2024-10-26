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

import com.enterprisemanager.backend.application.services.IOrderStatusService;
import com.enterprisemanager.backend.domain.entities.OrderStatus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/Order_Status")
public class OrderStatusController {
    @Autowired
    private IOrderStatusService orderStatusService;

    @GetMapping
    public List<OrderStatus> list() {
        return orderStatusService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<OrderStatus> orderStatusOptional = orderStatusService.findById(id);
        if(orderStatusOptional.isPresent()){
            return ResponseEntity.ok(orderStatusOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody OrderStatus orderStatus, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
            }
        return ResponseEntity.status(HttpStatus.CREATED).body(orderStatusService.save(orderStatus));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody OrderStatus orderStatus, @PathVariable Long id, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<OrderStatus> orderStatusOptional = orderStatusService.update(id, orderStatus);
        if (orderStatusOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(orderStatusOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<OrderStatus> orderStatusOptional = orderStatusService.delete(id);
        if (orderStatusOptional.isPresent()) {
            return ResponseEntity.ok(orderStatusOptional.orElseThrow());
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
