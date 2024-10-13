package com.enterprisemanager.backend.domain.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "service_approval")
@Data
public class ServiceApproval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private WorkOrder workOrder;
    
     @ManyToOne
     private ServiceBranch serviceBranches;

    @Column(length = 200, nullable = true)
    private String report;

    @Column(length = 200, nullable = true)
    private String solution;

    @ManyToOne
    private ApprovalStatus approvalStatus;
}
