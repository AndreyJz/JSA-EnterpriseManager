package com.enterprisemanager.backend.domain.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "service_order")
@Data
public class ServiceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "timestamp")
    private String orderDate;

    @ManyToOne
    @NotNull(message = "You have to add a Customer")
    private Person customer;

    @ManyToOne
    @NotNull(message = "You have to add an Employee")
    private Person employee;
    
    @ManyToOne
    @NotNull(message = "You have to add a Order Status")
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "serviceOrder")
    @JsonIgnore
    private List<WorkOrder> workOrder;

    @OneToMany(mappedBy = "serviceOrder")
    @JsonIgnore
    private List<OrderDetail> orderDetail;
}
