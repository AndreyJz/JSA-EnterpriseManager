package com.enterprisemanager.backend.infrastructure.repositories.phonetype;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprisemanager.backend.application.services.IPhoneTypeService;
import com.enterprisemanager.backend.domain.entities.PhoneType;

@Service
public class PhoneTypeServiceImpl implements IPhoneTypeService{

    @Autowired
    private PhoneTypeRepository phoneTypeRepository;

    @Transactional(readOnly = true)
    @Override
    public List<PhoneType> findAll() {
        return (List<PhoneType>) phoneTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<PhoneType> findById(Long id) {
        return phoneTypeRepository.findById(id);
    }

    @Transactional
    @Override
    public PhoneType save(PhoneType phoneType) {
        return phoneTypeRepository.save(phoneType);
    }

    @Transactional
    @Override
    public Optional<PhoneType> update(Long id, PhoneType phoneType) {
        Optional<PhoneType> phoneTypeOld = phoneTypeRepository.findById(id);
        if(phoneTypeOld.isPresent()){
            PhoneType phoneTypeDb = phoneTypeOld.orElseThrow();  
            phoneTypeDb.setName(phoneType.getName());
            return Optional.of(phoneTypeRepository.save(phoneTypeDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<PhoneType> delete(Long id) {
        Optional<PhoneType> phoneTypeOptional = phoneTypeRepository.findById(id);
        phoneTypeOptional.ifPresent(phoneTypeDb -> {
            phoneTypeRepository.delete(phoneTypeDb);
        });
        return phoneTypeOptional;
    }

}
