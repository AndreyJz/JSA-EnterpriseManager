package com.enterprisemanager.backend.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_details")
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ServiceBranch serviceBranch;

    @ManyToOne
    private ServiceOrder serviceOrder;

    @Column(columnDefinition = "DECIMAL(16,2)")
    private Float serviceValue;
}
