package com.enterprisemanager.backend.domain.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "service_approval")
@Data
public class ServiceApproval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "You have to add a Work order")
    private WorkOrder workOrder;
    
    @ManyToOne
    @NotNull(message = "You have to add a Service Branch")
    private ServiceBranch serviceBranch;

    @Column(length = 200, nullable = true)
    private String report;

    @Column(length = 200, nullable = true)
    private String solution;

    @ManyToOne
    @NotNull(message = "You have to add a Approval Status")
    private ApprovalStatus approvalStatus;
}
