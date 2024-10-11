package com.enterprisemanager.backend.domain.entities;

import com.enterprisemanager.backend.domain.dtos.ServiceBranchPk;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "service_branches")
public class ServiceBranch {
    @EmbeddedId
    private ServiceBranchPk id;

    @ManyToOne
    @JoinColumn(name = "service_id", insertable = false, updatable = false)
    private Service services;

    @ManyToOne
    @JoinColumn(name = "branch_id", insertable = false, updatable = false)
    private Branch branches;

    @Column(columnDefinition = "DECIMAL(16,2)",nullable = false)
    private Double serviceValue;
}
