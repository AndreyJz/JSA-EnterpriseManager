package com.enterprisemanager.backend.infrastructure.repositories.city;

import com.enterprisemanager.backend.application.services.ICityService;
import com.enterprisemanager.backend.domain.entities.City;
import com.enterprisemanager.backend.infrastructure.repositories.city.CityRepository;
import com.enterprisemanager.backend.infrastructure.utils.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    @Transactional
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<City> findById(Long id) {
        return Optional.ofNullable(cityRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("City with id: " + id + " not found :c")));
    }


    @Override
    @Transactional(readOnly = true)
    public List<City> findAll() {
        return (List<City>) cityRepository.findAll();
    }

    @Override
    public City update(Long id, City city) {
        Optional<City> cityOptional = cityRepository.findById(id);
        if (cityOptional.isPresent()) {
            City cityToUpdate = cityRepository.save(city);
            cityToUpdate.setName(city.getName());
            cityToUpdate.setRegion(city.getRegion());
            return cityRepository.save(cityToUpdate);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<City> cityOptional = cityRepository.findById(id);
        if (cityOptional.isPresent()) {
            cityRepository.delete(cityOptional.get());
        }
    }
}
