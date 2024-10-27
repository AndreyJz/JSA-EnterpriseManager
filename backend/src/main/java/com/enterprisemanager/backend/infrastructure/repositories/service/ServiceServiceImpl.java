package com.enterprisemanager.backend.infrastructure.repositories.service;

import com.enterprisemanager.backend.application.services.IServiceService;
import com.enterprisemanager.backend.infrastructure.utils.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceServiceImpl implements IServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    @Transactional
    public com.enterprisemanager.backend.domain.entities.Service save(com.enterprisemanager.backend.domain.entities.Service service) {
        return serviceRepository.save(service);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<com.enterprisemanager.backend.domain.entities.Service> findById(Long id) {
        return Optional.ofNullable(serviceRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Service with id: " + id + " not found :c")));
    }


    @Override
    @Transactional(readOnly = true)
    public List<com.enterprisemanager.backend.domain.entities.Service> findAll() {
        return (List<com.enterprisemanager.backend.domain.entities.Service>) serviceRepository.findAll();
    }

    @Override
    public List<com.enterprisemanager.backend.domain.entities.Service> findWithoutBranch() {
        return (List<com.enterprisemanager.backend.domain.entities.Service>) serviceRepository.findWithoutBranch();
    }

    @Override
    public com.enterprisemanager.backend.domain.entities.Service update(Long id, com.enterprisemanager.backend.domain.entities.Service service) {
        Optional<com.enterprisemanager.backend.domain.entities.Service> serviceOptional = serviceRepository.findById(id);
        if (serviceOptional.isPresent()) {
            com.enterprisemanager.backend.domain.entities.Service serviceToUpdate = serviceRepository.save(service);
            serviceToUpdate.setName(service.getName());
            serviceToUpdate.setRequiresSupply(service.getRequiresSupply());
            return serviceRepository.save(serviceToUpdate);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<com.enterprisemanager.backend.domain.entities.Service> serviceOptional = serviceRepository.findById(id);
        if (serviceOptional.isPresent()) {
            serviceRepository.delete(serviceOptional.get());
        }
    }
}
