package com.enterprisemanager.backend.infrastructure.controllers;

import com.enterprisemanager.backend.application.services.IBranchService;
import com.enterprisemanager.backend.domain.entities.Branch;
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
@RequestMapping("api/branch")
public class BranchController {
    @Autowired
    private IBranchService branchService;

    @GetMapping
    public List<Branch> getAll() { return branchService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Branch> branchOptional = branchService.findById(id);
        if (branchOptional.isPresent()) {
            return ResponseEntity.ok(branchOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Branch branch, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(branchService.save(branch));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Branch branch, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Branch> branchOptional = branchService.findById(id);
        if (branchOptional.isPresent()) {
            branch.setId(id);
//            branch.setName(branchOptional.get().getName());
            return ResponseEntity.ok(branchService.update(id,branch));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Branch> branchOptional = branchService.findById(id);
        if (branchOptional.isPresent()) {
            branchService.delete(id);
            return ResponseEntity.ok(branchOptional.orElseThrow());
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
