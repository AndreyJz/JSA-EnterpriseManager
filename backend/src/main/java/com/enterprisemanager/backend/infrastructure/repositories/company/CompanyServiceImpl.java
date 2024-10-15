package com.enterprisemanager.backend.infrastructure.repositories.company;

import com.enterprisemanager.backend.application.services.ICompanyService;
import com.enterprisemanager.backend.domain.entities.Company;
import com.enterprisemanager.backend.infrastructure.utils.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Transactional
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Company> findById(Long id) {
        return Optional.ofNullable(companyRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Company with id: " + id + " not found :c")));
    }


    @Override
    @Transactional(readOnly = true)
    public List<Company> findAll() {
        return (List<Company>) companyRepository.findAll();
    }

    @Override
    public Company update(Long id, Company company) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company companyToUpdate = companyRepository.save(company);
            companyToUpdate.setName(company.getName());
            return companyRepository.save(companyToUpdate);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            companyRepository.delete(companyOptional.get());
        }
    }
}
