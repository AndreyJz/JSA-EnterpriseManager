package com.enterprisemanager.backend.infrastructure.repositories.servicebranch;

import com.enterprisemanager.backend.application.services.IServiceBranchService;
import com.enterprisemanager.backend.domain.dtos.ServiceBranchPk;
import com.enterprisemanager.backend.domain.entities.ServiceBranch;
import com.enterprisemanager.backend.infrastructure.utils.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceBranchServiceImpl implements IServiceBranchService {

    @Autowired
    private ServiceBranchRepository serviceBranchRepository;

    @Override
    @Transactional
    public ServiceBranch save(ServiceBranch serviceBranch) {
        return serviceBranchRepository.save(serviceBranch);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServiceBranch> findById(ServiceBranchPk id) {
        return Optional.ofNullable(serviceBranchRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("ServiceBranch with id: " + id + " not found :c")));
    }


    @Override
    @Transactional(readOnly = true)
    public List<ServiceBranch> findAll() {
        return (List<ServiceBranch>) serviceBranchRepository.findAll();
    }

    @Override
    public ServiceBranch update(ServiceBranchPk id, ServiceBranch serviceBranch) {
        Optional<ServiceBranch> serviceBranchOptional = serviceBranchRepository.findById(id);
        if (serviceBranchOptional.isPresent()) {
            ServiceBranch serviceBranchToUpdate = serviceBranchRepository.save(serviceBranch);
            serviceBranchToUpdate.setServiceValue(serviceBranch.getServiceValue());
            return serviceBranchRepository.save(serviceBranchToUpdate);
        }
        return null;
    }

    @Override
    public void delete(ServiceBranchPk id) {
        Optional<ServiceBranch> serviceBranchOptional = serviceBranchRepository.findById(id);
        if (serviceBranchOptional.isPresent()) {
            serviceBranchRepository.delete(serviceBranchOptional.get());
        }
    }
}
