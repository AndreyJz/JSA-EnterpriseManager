package com.enterprisemanager.backend.infrastructure.repositories.serviceorder;

import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.domain.entities.ServiceApproval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprisemanager.backend.application.services.IServiceOrderService;
import com.enterprisemanager.backend.domain.entities.ServiceOrder;
import java.time.LocalDateTime;



@Service
public class ServiceOrderImpl implements IServiceOrderService {

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ServiceOrder> findAll() {
        return (List<ServiceOrder>) serviceOrderRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ServiceOrder> findById(Long id) {
        return serviceOrderRepository.findById(id);
    }

    @Transactional
    @Override
    public ServiceOrder save(ServiceOrder serviceOrder) {
        serviceOrder.setOrderDate(LocalDateTime.now());
        return serviceOrderRepository.save(serviceOrder);
    }

    @Transactional
    @Override
    public List<ServiceOrder> findAllByEmployeeId(String id) {
        return serviceOrderRepository.findAllByEmployeeId(id);
    }

    @Transactional
    @Override
    public List<ServiceOrder> findAllByCustomerId(String id) {
        return serviceOrderRepository.findAllByCustomerId(id);
    }

    @Transactional
    @Override
    public Optional<ServiceOrder> update(Long id, ServiceOrder serviceOrder) {
        Optional<ServiceOrder> serviceOrderOld = serviceOrderRepository.findById(id);
        if(serviceOrderOld.isPresent()){
            ServiceOrder serviceOrderDb = serviceOrderOld.orElseThrow();  
            serviceOrderDb.setOrderStatus(serviceOrder.getOrderStatus());
            serviceOrderDb.setCustomer(serviceOrder.getCustomer());
            serviceOrderDb.setEmployee(serviceOrder.getEmployee());


            return Optional.of(serviceOrderRepository.save(serviceOrderDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<ServiceOrder> delete(Long id) {
        Optional<ServiceOrder> serviceOrderOptional = serviceOrderRepository.findById(id);
        serviceOrderOptional.ifPresent(serviceOrderDb -> {
            serviceOrderRepository.delete(serviceOrderDb);
        });
        return serviceOrderOptional;
    }
}
