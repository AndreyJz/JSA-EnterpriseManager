package com.enterprisemanager.backend.infrastructure.repositories.serviceapproval;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprisemanager.backend.application.services.IServiceApprovalService;
import com.enterprisemanager.backend.domain.entities.ServiceApproval;


@Service
public class ServiceApprovalImpl implements IServiceApprovalService {

    @Autowired
    private ServiceApprovalRepository serviceApprovalRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ServiceApproval> findAll() {
        return (List<ServiceApproval>) serviceApprovalRepository.findAll();
    }

    @Override
    public List<ServiceApproval> findAllByEmployeeId(String id) {
        return (List<ServiceApproval>) serviceApprovalRepository.findAllByEmployeeId(id);
    }

    @Override
    public List<ServiceApproval> findAllByCustomerId(String id) {
        return (List<ServiceApproval>) serviceApprovalRepository.findAllByCustomerId(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ServiceApproval> findById(Long id) {
        return serviceApprovalRepository.findById(id);
    }

    @Transactional
    @Override
    public ServiceApproval save(ServiceApproval serviceApproval) {
        return serviceApprovalRepository.save(serviceApproval);
    }

    @Transactional
    @Override
    public Optional<ServiceApproval> update(Long id, ServiceApproval serviceApproval) {
        Optional<ServiceApproval> serviceApprovalOld = serviceApprovalRepository.findById(id);
        if(serviceApprovalOld.isPresent()){
            ServiceApproval serviceApprovalDb = serviceApprovalOld.orElseThrow();  

            serviceApprovalDb.setApprovalStatus(serviceApproval.getApprovalStatus());
            serviceApprovalDb.setServiceBranch(serviceApproval.getServiceBranch());
            serviceApprovalDb.setWorkOrder(serviceApproval.getWorkOrder());
            serviceApprovalDb.setReport(serviceApproval.getReport());
            serviceApprovalDb.setSolution(serviceApproval.getSolution());
            return Optional.of(serviceApprovalRepository.save(serviceApprovalDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<ServiceApproval> delete(Long id) {
        Optional<ServiceApproval> serviceApprovalOptional = serviceApprovalRepository.findById(id);
        serviceApprovalOptional.ifPresent(serviceApprovalDb -> {
            serviceApprovalRepository.delete(serviceApprovalDb);
        });
        return serviceApprovalOptional;
    }
}
