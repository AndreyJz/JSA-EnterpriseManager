package com.enterprisemanager.backend.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/supplyService")
public class SupplyServiceController {
    @Autowired
    private ISupplyServiceService supplyServiceService;

    @GetMapping
        public List<SupplyService> list() {
        return supplyServiceService.findAll();
    }

    @GetMapping("/{saleid}/{productid}")

    public ResponseEntity<?> view(@PathVariable Long saleid, @PathVariable Long productid){
        SupplyServiceId supplyServiceId = new SupplyServiceId(saleid, productid);
        Optional<SupplyService> supplyServiceOptional = supplyServiceService.findById(supplyServiceId);
        if(supplyServiceOptional.isPresent()){
            return ResponseEntity.ok(supplyServiceOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create (@RequestBody SupplyService supplyService){
        return ResponseEntity.status(HttpStatus.CREATED).body(supplyServiceService.save(supplyService));
    }

        
    @PutMapping("/{saleid}/{productid}")
    public ResponseEntity<?> update(@RequestBody SupplyService supplyService,@PathVariable Long saleid, @PathVariable Long productid) {
        SupplyServiceId supplyServiceId = new SupplyServiceId(saleid, productid);
        Optional<SupplyService> supplyServiceOptional = supplyServiceService.update(supplyServiceId, supplyService);
        if (supplyServiceOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(supplyServiceOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{saleid}/{productid}")
    public ResponseEntity<?> delete(@PathVariable Long saleid, @PathVariable Long productid) {
        SupplyServiceId supplyServiceId = new SupplyServiceId(saleid, productid);
        Optional<SupplyService> supplyServiceOptional = supplyServiceService.delete(supplyServiceId);
        if (supplyServiceOptional.isPresent()) {
            return ResponseEntity.ok(supplyServiceOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
