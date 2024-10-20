package com.enterprisemanager.backend.infrastructure.controllers;

import com.enterprisemanager.backend.application.services.IServiceService;
import com.enterprisemanager.backend.domain.entities.Service;
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
@RequestMapping("/api/service")
public class ServiceController {
    @Autowired
    private IServiceService serviceService;

    @GetMapping
    public List<Service> getAll() { return serviceService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Service> serviceOptional = serviceService.findById(id);
        if (serviceOptional.isPresent()) {
            return ResponseEntity.ok(serviceOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Service service, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceService.save(service));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Service service, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Service> serviceOptional = serviceService.findById(id);
        if (serviceOptional.isPresent()) {
            service.setId(id);
//            service.setName(serviceOptional.get().getName());
            return ResponseEntity.ok(serviceService.update(id,service));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Service> serviceOptional = serviceService.findById(id);
        if (serviceOptional.isPresent()) {
            serviceService.delete(id);
            return ResponseEntity.ok(serviceOptional.orElseThrow());
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
