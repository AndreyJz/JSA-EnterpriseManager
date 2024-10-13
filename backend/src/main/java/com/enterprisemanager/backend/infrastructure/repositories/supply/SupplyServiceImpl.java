package com.enterprisemanager.backend.infrastructure.repositories.supply;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprisemanager.backend.application.services.ISupplyService;
import com.enterprisemanager.backend.domain.entities.Supply;

@Service
public class SupplyServiceImpl implements ISupplyService{

    @Autowired
    private SupplyRepository supplyRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Supply> findAll() {
        return (List<Supply>) supplyRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Supply> findById(Long id) {
        return supplyRepository.findById(id);
    }

    @Transactional
    @Override
    public Supply save(Supply supply) {
        return supplyRepository.save(supply);
    }

    @Transactional
    @Override
    public Optional<Supply> update(Long id, Supply supply) {
        Optional<Supply> supplyOld = supplyRepository.findById(id);
        if(supplyOld.isPresent()){
            Supply supplyDb = supplyOld.orElseThrow();
            supplyDb.setBarcode(supply.getBarcode());
            supplyDb.setName(supply.getName());
            supplyDb.setPrice(supply.getPrice());
            supplyDb.setStock(supply.getStock());
            supplyDb.setStockMin(supply.getStockMin());
            supplyDb.setStockMax(supply.getStockMax());
            return Optional.of(supplyRepository.save(supplyDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Supply> delete(Long id) {
        Optional<Supply> supplyOptional = supplyRepository.findById(id);
        supplyOptional.ifPresent(supplyDb -> {
            supplyRepository.delete(supplyDb);
        });
        return supplyOptional;
    }
}
