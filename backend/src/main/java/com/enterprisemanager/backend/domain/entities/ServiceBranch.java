package com.enterprisemanager.backend.domain.entities;

import com.enterprisemanager.backend.domain.dtos.ServiceBranchPk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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
    private Float serviceValue;

    @OneToMany(mappedBy = "serviceBranches")
    @JsonIgnore
    private Set<SupplyService> supplyServices;

    @OneToMany(mappedBy = "serviceBranches")
    @JsonIgnore
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "serviceBranches")
    @JsonIgnore
    private Set<WorkOrderDetail> workOrderDetails;

    @OneToMany(mappedBy = "serviceBranches")
    @JsonIgnore
    private Set<ServiceApproval> serviceApprovals;
}
