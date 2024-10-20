package com.enterprisemanager.backend.infrastructure.controllers;

import com.enterprisemanager.backend.application.services.ICompanyService;
import com.enterprisemanager.backend.domain.entities.Company;
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
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private ICompanyService companyService;

    @GetMapping
    public List<Company> getAll() { return companyService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Company> companyOptional = companyService.findById(id);
        if (companyOptional.isPresent()) {
            return ResponseEntity.ok(companyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Company company, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.save(company));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Company company, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Company> companyOptional = companyService.findById(id);
        if (companyOptional.isPresent()) {
            company.setId(id);
//            company.setName(companyOptional.get().getName());
            return ResponseEntity.ok(companyService.update(id,company));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Company> companyOptional = companyService.findById(id);
        if (companyOptional.isPresent()) {
            companyService.delete(id);
            return ResponseEntity.ok(companyOptional.orElseThrow());
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
