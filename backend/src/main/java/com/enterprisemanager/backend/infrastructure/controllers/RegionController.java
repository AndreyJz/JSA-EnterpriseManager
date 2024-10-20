package com.enterprisemanager.backend.infrastructure.controllers;

import com.enterprisemanager.backend.application.services.IRegionService;
import com.enterprisemanager.backend.domain.entities.Region;
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
@RequestMapping("/api/region")
public class RegionController {
    @Autowired
    private IRegionService regionService;

    @GetMapping
    public List<Region> getAll() { return regionService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Region> regionOptional = regionService.findById(id);
        if (regionOptional.isPresent()) {
            return ResponseEntity.ok(regionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Region region, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(regionService.save(region));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Region region, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Region> regionOptional = regionService.findById(id);
        if (regionOptional.isPresent()) {
            region.setId(id);
//            region.setName(regionOptional.get().getName());
            return ResponseEntity.ok(regionService.update(id,region));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Region> regionOptional = regionService.findById(id);
        if (regionOptional.isPresent()) {
            regionService.delete(id);
            return ResponseEntity.ok(regionOptional.orElseThrow());
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
