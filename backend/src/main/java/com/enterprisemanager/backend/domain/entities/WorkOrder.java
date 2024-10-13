package com.enterprisemanager.backend.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "work_orders")
public class WorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ServiceBranch serviceBranches;

    @ManyToOne
    private ServiceOrder serviceOrder;
}
