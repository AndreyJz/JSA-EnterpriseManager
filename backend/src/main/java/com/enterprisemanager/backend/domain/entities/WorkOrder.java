package com.enterprisemanager.backend.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "work_orders")
@Data
public class WorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = true)
    @NotNull(message = "You have to add a Number for the order")
    private String workOrderNum;

    @Column(columnDefinition = "timestamp")
    private String assignDate;

    @ManyToOne
    @NotNull(message = "You have to add a Service order")
    private ServiceOrder serviceOrder;
}
