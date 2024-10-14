package com.enterprisemanager.backend.infrastructure.repositories.branch;

import com.enterprisemanager.backend.application.services.IBranchService;
import com.enterprisemanager.backend.domain.entities.Branch;
import com.enterprisemanager.backend.infrastructure.utils.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements IBranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    @Transactional
    public Branch save(Branch Branch) {
        Branch.setCreationDate(LocalDateTime.now());
        return branchRepository.save(Branch);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Branch> findById(Long id) {
        return Optional.ofNullable(branchRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Branch with id: " + id + " not found :c")));
    }


    @Override
    @Transactional(readOnly = true)
    public List<Branch> findAll() {
        return (List<Branch>) branchRepository.findAll();
    }

    @Override
    public Branch update(Long id, Branch branch) {
        Optional<Branch> BranchOptional = branchRepository.findById(id);
        if (BranchOptional.isPresent()) {
            Branch BranchToUpdate = branchRepository.save(branch);
            BranchToUpdate.setName(branch.getName());
            BranchToUpdate.setNit(branch.getNit());
//            BranchToUpdate.setCreationDate(branch.getCreationDate());
            BranchToUpdate.setCity(branch.getCity());
            BranchToUpdate.setCompany(branch.getCompany());
            return branchRepository.save(BranchToUpdate);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Branch> BranchOptional = branchRepository.findById(id);
        if (BranchOptional.isPresent()) {
            branchRepository.delete(BranchOptional.get());
        }
    }
}
