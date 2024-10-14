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

import com.enterprisemanager.backend.application.services.ISupplyServiceService;
import com.enterprisemanager.backend.domain.entities.SupplyService;
import com.enterprisemanager.backend.domain.entities.SupplyServiceId;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/supplyService")
public class SupplyServiceController {
    @Autowired
    private ISupplyServiceService supplyServiceService;

    @GetMapping
        public List<SupplyService> list() {
        return supplyServiceService.findAll();
    }

    @GetMapping("/{branchid}/{supplyid}/{serviceid}")

    public ResponseEntity<?> view(@PathVariable Long branchid, @PathVariable Long supplyid, @PathVariable Long serviceid){
        SupplyServiceId supplyServiceId = new SupplyServiceId(branchid, supplyid, serviceid);
        Optional<SupplyService> supplyServiceOptional = supplyServiceService.findById(supplyServiceId);
        if(supplyServiceOptional.isPresent()){
            return ResponseEntity.ok(supplyServiceOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody SupplyService supplyService, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
            }
        return ResponseEntity.status(HttpStatus.CREATED).body(supplyServiceService.save(supplyService));
    }

        
    @PutMapping("/{branchid}/{supplyid}/{serviceid}")
    public ResponseEntity<?> update(@Valid @RequestBody SupplyService supplyService,@PathVariable Long branchid, @PathVariable Long supplyid, @PathVariable Long serviceid, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
            }
        SupplyServiceId supplyServiceId = new SupplyServiceId(branchid, supplyid, serviceid);
        Optional<SupplyService> supplyServiceOptional = supplyServiceService.update(supplyServiceId, supplyService);
        if (supplyServiceOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(supplyServiceOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{branchid}/{supplyid}/{serviceid}")
    public ResponseEntity<?> delete(@PathVariable Long branchid, @PathVariable Long supplyid, @PathVariable Long serviceid) {
        SupplyServiceId supplyServiceId = new SupplyServiceId(branchid, supplyid, serviceid);
        Optional<SupplyService> supplyServiceOptional = supplyServiceService.delete(supplyServiceId);
        if (supplyServiceOptional.isPresent()) {
            return ResponseEntity.ok(supplyServiceOptional.orElseThrow());
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
