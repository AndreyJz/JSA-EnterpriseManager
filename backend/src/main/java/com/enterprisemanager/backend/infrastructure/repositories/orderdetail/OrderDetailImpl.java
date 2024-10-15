package com.enterprisemanager.backend.infrastructure.repositories.orderdetail;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprisemanager.backend.application.services.IOrderDetailService;
import com.enterprisemanager.backend.domain.entities.OrderDetail;


@Service
public class OrderDetailImpl implements IOrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Transactional(readOnly = true)
    @Override
    public List<OrderDetail> findAll() {
        return (List<OrderDetail>) orderDetailRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<OrderDetail> findById(Long id) {
        return orderDetailRepository.findById(id);
    }

    @Transactional
    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Transactional
    @Override
    public Optional<OrderDetail> update(Long id, OrderDetail orderDetail) {
        Optional<OrderDetail> orderDetailOld = orderDetailRepository.findById(id);
        if(orderDetailOld.isPresent()){
            OrderDetail orderDetailDb = orderDetailOld.orElseThrow();  
            orderDetailDb.setServiceBranch(orderDetail.getServiceBranch());
            orderDetailDb.setServiceOrder(orderDetail.getServiceOrder());
            orderDetailDb.setServiceValue(orderDetail.getServiceValue());
            return Optional.of(orderDetailRepository.save(orderDetailDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<OrderDetail> delete(Long id) {
        Optional<OrderDetail> orderDetailOptional = orderDetailRepository.findById(id);
        orderDetailOptional.ifPresent(orderDetailDb -> {
            orderDetailRepository.delete(orderDetailDb);
        });
        return orderDetailOptional;
    }
}
