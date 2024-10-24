package com.enterprisemanager.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "work_order_detail")
@Data
public class WorkOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "You have to add a Work order")
    private WorkOrder workOrder;
    
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Person person;
    
    @Column(columnDefinition = "datetime")
    private String date;

    @ManyToOne
    @NotNull(message = "You have to add a Work order Detail status")
    private WorkOrderDetailStatus workOrderDetailStatus;
    
    @ManyToOne
    @NotNull(message = "You have to add a Service Branch")

    private ServiceBranch serviceBranch;


}
