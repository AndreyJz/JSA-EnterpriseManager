package com.enterprisemanager.backend.domain.entities;

import com.enterprisemanager.backend.domain.dtos.ServiceBranchPk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "You have to add a Service Id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "branch_id", insertable = false, updatable = false)
    @NotNull(message = "You have to add a Branch Id")
    private Branch branch;

    @Column(columnDefinition = "DECIMAL(16,2)",nullable = false)
    @NotNull(message = "You have to add a Service Value")
    private Float serviceValue;

    @OneToMany(mappedBy = "serviceBranch")
    @JsonIgnore
    private Set<SupplyService> supplyService;

    @OneToMany(mappedBy = "serviceBranch")
    @JsonIgnore
    private Set<OrderDetail> orderDetail;

    @OneToMany(mappedBy = "serviceBranch")
    @JsonIgnore
    private Set<WorkOrderDetail> workOrderDetail;

    @OneToMany(mappedBy = "serviceBranch")
    @JsonIgnore
    private Set<ServiceApproval> serviceApproval;
}
