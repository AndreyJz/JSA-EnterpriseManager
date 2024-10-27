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

import com.enterprisemanager.backend.application.services.IOrderDetailService;
import com.enterprisemanager.backend.domain.entities.OrderDetail;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/Order_Details")
public class OrderDetailController {
    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping
    public List<OrderDetail> list() {
        return orderDetailService.findAll();
    }

    @GetMapping("/ServiceOrder_{id}")
    public List<OrderDetail> listByServiceOrder(@PathVariable Long id) {
        return orderDetailService.findAllByServiceOrder(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<OrderDetail> orderDetailOptional = orderDetailService.findById(id);
        if(orderDetailOptional.isPresent()){
            return ResponseEntity.ok(orderDetailOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody OrderDetail orderDetail, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
            }
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailService.save(orderDetail));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody OrderDetail orderDetail, @PathVariable Long id, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<OrderDetail> orderDetailOptional = orderDetailService.update(id, orderDetail);
        if (orderDetailOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<OrderDetail> orderDetailOptional = orderDetailService.delete(id);
        if (orderDetailOptional.isPresent()) {
            return ResponseEntity.ok(orderDetailOptional.orElseThrow());
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
