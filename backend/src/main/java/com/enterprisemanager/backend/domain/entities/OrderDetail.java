package com.enterprisemanager.backend.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ServiceBranch serviceBranch;

    @ManyToOne
    private ServiceOrder serviceOrder;
}
