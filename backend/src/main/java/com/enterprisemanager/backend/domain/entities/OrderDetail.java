package com.enterprisemanager.backend.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "order_details")
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "You have to add a Service Branch")
    private ServiceBranch serviceBranch;

    @ManyToOne
    @NotNull(message = "You have to add a Service Order")
    private ServiceOrder serviceOrder;

    @Column(columnDefinition = "DECIMAL(16,2)")
    private Float serviceValue;
}
