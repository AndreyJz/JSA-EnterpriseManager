package com.enterprisemanager.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "work_order_detail")
@Data
public class WorkOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private WorkOrder workOrder;
    
     @ManyToOne
     @JoinColumn(name = "employee_id")
     private Person person;
    
    @Column(columnDefinition = "datetime")
    private String date;

    @ManyToOne
    private WorkOrderDetailStatus workOrderDetailStatus;
    
      @ManyToOne
      private ServiceBranch serviceBranch;


}
