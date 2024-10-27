package com.enterprisemanager.backend.infrastructure.repositories.phone;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprisemanager.backend.application.services.IPhoneService;
import com.enterprisemanager.backend.domain.entities.Phone;

@Service
public class PhoneServiceImpl implements IPhoneService{

    @Autowired
    private PhoneRepository phoneRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Phone> findAll() {
        return (List<Phone>) phoneRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Phone> findById(Long id) {
        return phoneRepository.findById(id);
    }

    @Transactional
    @Override
    public Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Transactional
    @Override
    public Optional<Phone> update(Long id, Phone phone) {
        Optional<Phone> phoneOld = phoneRepository.findById(id);
        if(phoneOld.isPresent()){
            Phone phoneDb = phoneOld.orElseThrow();
            phoneDb.setNumber(phone.getNumber());
            phoneDb.setPhoneType(phone.getPhoneType());
            phoneDb.setPerson(phone.getPerson());
            return Optional.of(phoneRepository.save(phoneDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Phone> delete(Long id) {
        Optional<Phone> phoneOptional = phoneRepository.findById(id);
        phoneOptional.ifPresent(phoneDb -> {
            phoneRepository.delete(phoneDb);
        });
        return phoneOptional;
    }

    @Override
    public List<Phone> findAllByCustomerId(String id) {
        return (List<Phone>) phoneRepository.findAllByCustomerId(id);
    }
}
