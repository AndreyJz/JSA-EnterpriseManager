package com.enterprisemanager.backend.infrastructure.repositories.supplyservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprisemanager.backend.application.services.ISupplyServiceService;
import com.enterprisemanager.backend.domain.entities.SupplyService;
import com.enterprisemanager.backend.domain.entities.SupplyServiceId;

@Service
public class SupplyServiceServiceImpl implements ISupplyServiceService{

    @Autowired
    private SupplyServiceRepository supplyServiceRepository;

    @Transactional(readOnly = true)
    @Override
    public List<SupplyService> findAll() {
        return (List<SupplyService>) supplyServiceRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<SupplyService> findById(SupplyServiceId id) {
        return supplyServiceRepository.findById(id);
    }

    @Transactional
    @Override
    public SupplyService save(SupplyService supplyService) {
        return supplyServiceRepository.save(supplyService);
    }

    @Transactional
    @Override
    public Optional<SupplyService> update(SupplyServiceId id, SupplyService supplyService) {
        Optional<SupplyService> supplyServiceOld = supplyServiceRepository.findById(id);
        if(supplyServiceOld.isPresent()){
            SupplyService supplyServiceDb = supplyServiceOld.orElseThrow();
            supplyServiceDb.setQuantity(supplyService.getQuantity());
            return Optional.of(supplyServiceRepository.save(supplyServiceDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<SupplyService> delete(SupplyServiceId id) {
        Optional<SupplyService> supplyServiceOptional = supplyServiceRepository.findById(id);
        supplyServiceOptional.ifPresent(supplyServiceDb -> {
            supplyServiceRepository.delete(supplyServiceDb);
        });
        return supplyServiceOptional;
    }
}
