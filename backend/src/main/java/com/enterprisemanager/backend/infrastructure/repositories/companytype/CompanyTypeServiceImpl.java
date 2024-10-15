package com.enterprisemanager.backend.infrastructure.repositories.companytype;

import com.enterprisemanager.backend.application.services.ICompanyTypeService;
import com.enterprisemanager.backend.domain.entities.CompanyType;
import com.enterprisemanager.backend.infrastructure.utils.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyTypeServiceImpl implements ICompanyTypeService {

    @Autowired
    private CompanyTypeRepository companyTypeRepository;

    @Override
    @Transactional
    public CompanyType save(CompanyType companyType) {
        return companyTypeRepository.save(companyType);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CompanyType> findById(Long id) {
        return Optional.ofNullable(companyTypeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("CompanyType with id: " + id + " not found :c")));
    }


    @Override
    @Transactional(readOnly = true)
    public List<CompanyType> findAll() {
        return (List<CompanyType>) companyTypeRepository.findAll();
    }

    @Override
    public CompanyType update(Long id, CompanyType companyType) {
        Optional<CompanyType> companyTypeOptional = companyTypeRepository.findById(id);
        if (companyTypeOptional.isPresent()) {
            CompanyType companyTypeToUpdate = companyTypeRepository.save(companyType);
            companyTypeToUpdate.setDescription(companyType.getDescription());
            return companyTypeRepository.save(companyTypeToUpdate);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<CompanyType> companyTypeOptional = companyTypeRepository.findById(id);
        if (companyTypeOptional.isPresent()) {
            companyTypeRepository.delete(companyTypeOptional.get());
        }
    }
}
