package com.enterprisemanager.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "supply_service") 
@Data
public class SupplyService {
    @EmbeddedId
    private SupplyServiceId id;

    @ManyToOne
//    @JoinColumn(name = "service_id",insertable=false, updatable=false)
    private ServiceBranch serviceBranches;

    @ManyToOne
    @JoinColumn(name = "supply_id", insertable=false, updatable=false)
    private Supply supply;

    @Column(nullable = false)
    private int quantity;

    
}
