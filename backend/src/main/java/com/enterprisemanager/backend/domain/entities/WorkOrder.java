package com.enterprisemanager.backend.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "work_orders")
@Data
public class WorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = true)
    private String workOderNum;

    @Column(columnDefinition = "timestamp")
    private String assignDate;

    @ManyToOne
    private ServiceOrder serviceOrder;
}
