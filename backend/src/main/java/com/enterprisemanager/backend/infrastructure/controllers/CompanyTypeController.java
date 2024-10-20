package com.enterprisemanager.backend.infrastructure.controllers;

import com.enterprisemanager.backend.application.services.ICompanyTypeService;
import com.enterprisemanager.backend.domain.entities.CompanyType;
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
@RequestMapping("/api/companyType")
public class CompanyTypeController {
    @Autowired
    private ICompanyTypeService companyTypeService;

    @GetMapping
    public List<CompanyType> getAll() { return companyTypeService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<CompanyType> companyTypeOptional = companyTypeService.findById(id);
        if (companyTypeOptional.isPresent()) {
            return ResponseEntity.ok(companyTypeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CompanyType companyType, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(companyTypeService.save(companyType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CompanyType companyType, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<CompanyType> companyTypeOptional = companyTypeService.findById(id);
        if (companyTypeOptional.isPresent()) {
            companyType.setId(id);
//            companyType.setName(companyTypeOptional.get().getName());
            return ResponseEntity.ok(companyTypeService.update(id,companyType));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<CompanyType> companyTypeOptional = companyTypeService.findById(id);
        if (companyTypeOptional.isPresent()) {
            companyTypeService.delete(id);
            return ResponseEntity.ok(companyTypeOptional.orElseThrow());
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
