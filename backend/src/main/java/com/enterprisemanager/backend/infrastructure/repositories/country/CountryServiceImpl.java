package com.enterprisemanager.backend.infrastructure.repositories.country;

import com.enterprisemanager.backend.application.services.ICountryService;
import com.enterprisemanager.backend.domain.entities.Country;
import com.enterprisemanager.backend.infrastructure.utils.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements ICountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    @Transactional
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Country> findById(Long id) {
        return Optional.ofNullable(countryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Country with id: " + id + " not found :c")));
    }


    @Override
    @Transactional(readOnly = true)
    public List<Country> findAll() {
        return (List<Country>) countryRepository.findAll();
    }

    @Override
    public Country update(Long id, Country country) {
        Optional<Country> countryOptional = countryRepository.findById(id);
        if (countryOptional.isPresent()) {
            Country countryToUpdate = countryRepository.save(country);
            countryToUpdate.setName(country.getName());
            return countryRepository.save(countryToUpdate);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Country> countryOptional = countryRepository.findById(id);
        if (countryOptional.isPresent()) {
            countryRepository.delete(countryOptional.get());
        }
    }
}
