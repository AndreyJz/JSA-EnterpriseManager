package com.enterprisemanager.backend.infrastructure.repositories.region;

import com.enterprisemanager.backend.application.services.IRegionService;
import com.enterprisemanager.backend.domain.entities.Region;
import com.enterprisemanager.backend.infrastructure.repositories.region.RegionRepository;
import com.enterprisemanager.backend.infrastructure.utils.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    @Transactional
    public Region save(Region region) {
        return regionRepository.save(region);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Region> findById(Long id) {
        return Optional.ofNullable(regionRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Region with id: " + id + " not found :c")));
    }


    @Override
    @Transactional(readOnly = true)
    public List<Region> findAll() {
        return (List<Region>) regionRepository.findAll();
    }

    @Override
    public Region update(Long id, Region region) {
        Optional<Region> regionOptional = regionRepository.findById(id);
        if (regionOptional.isPresent()) {
            Region regionToUpdate = regionRepository.save(region);
            regionToUpdate.setName(region.getName());
            regionToUpdate.setCountry(region.getCountry());
            return regionRepository.save(regionToUpdate);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Region> regionOptional = regionRepository.findById(id);
        if (regionOptional.isPresent()) {
            regionRepository.delete(regionOptional.get());
        }
    }
}
