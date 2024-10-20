package com.enterprisemanager.backend.infrastructure.controllers;

import com.enterprisemanager.backend.application.services.ICountryService;
import com.enterprisemanager.backend.domain.entities.Country;
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
@RequestMapping("/api/country")
public class CountryController {
    @Autowired
    private ICountryService countryService;

    @GetMapping
    public List<Country> getAll() { return countryService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Country> countryOptional = countryService.findById(id);
        if (countryOptional.isPresent()) {
            return ResponseEntity.ok(countryOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Country country, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.save(country));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Country country, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Country> countryOptional = countryService.findById(id);
        if (countryOptional.isPresent()) {
            country.setId(id);
//            country.setName(countryOptional.get().getName());
            return ResponseEntity.ok(countryService.update(id,country));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Country> countryOptional = countryService.findById(id);
        if (countryOptional.isPresent()) {
            countryService.delete(id);
            return ResponseEntity.ok(countryOptional.orElseThrow());
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
