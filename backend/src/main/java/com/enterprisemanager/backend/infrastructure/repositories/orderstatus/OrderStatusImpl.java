package com.enterprisemanager.backend.infrastructure.repositories.orderstatus;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprisemanager.backend.application.services.IOrderStatusService;
import com.enterprisemanager.backend.domain.entities.OrderStatus;


@Service
public class OrderStatusImpl implements IOrderStatusService {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Transactional(readOnly = true)
    @Override
    public List<OrderStatus> findAll() {
        return (List<OrderStatus>) orderStatusRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<OrderStatus> findById(Long id) {
        return orderStatusRepository.findById(id);
    }

    @Transactional
    @Override
    public OrderStatus save(OrderStatus orderStatus) {
        return orderStatusRepository.save(orderStatus);
    }

    @Transactional
    @Override
    public Optional<OrderStatus> update(Long id, OrderStatus orderStatus) {
        Optional<OrderStatus> orderStatusOld = orderStatusRepository.findById(id);
        if(orderStatusOld.isPresent()){
            OrderStatus orderStatusDb = orderStatusOld.orElseThrow();  
            orderStatusDb.setName(orderStatus.getName());
            return Optional.of(orderStatusRepository.save(orderStatusDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<OrderStatus> delete(Long id) {
        Optional<OrderStatus> orderStatusOptional = orderStatusRepository.findById(id);
        orderStatusOptional.ifPresent(orderStatusDb -> {
            orderStatusRepository.delete(orderStatusDb);
        });
        return orderStatusOptional;
    }
}
