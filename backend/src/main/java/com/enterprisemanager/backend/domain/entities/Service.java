package com.enterprisemanager.backend.domain.entities;

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
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Por favor agregue información")
    private String name;

    @Column(nullable = false)
    private Boolean requiresI;

    @OneToMany(mappedBy = "services")
    @JsonIgnore
    private Set<ServiceBranch> serviceBranches;

//    @OneToMany(mappedBy = "services")
//    @JsonIgnore
//    private Set<SupplyService> supplyServices;

//    @OneToMany(mappedBy = "services")
//    @JsonIgnore
//    private Set<OrderDetail> orderDetails;

//    @OneToMany(mappedBy = "services")
//    @JsonIgnore
//    private Set<WorkOrderDetail> workOrderDetails;

//    @OneToMany(mappedBy = "services")
//    @JsonIgnore
//    private Set<ServiceApproval> serviceApprovals;
}
