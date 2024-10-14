package com.enterprisemanager.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Table(name = "supply_service")
@Data
public class SupplyService {
    @EmbeddedId
    private SupplyServiceId id;
    
    @ManyToOne
    @MapsId("serviceBranchId")
    @JoinColumns({
        @JoinColumn(name = "service_branches_branch_id", referencedColumnName = "branch_id", insertable = false, updatable = false),
        @JoinColumn(name = "service_branches_service_id", referencedColumnName = "service_id", insertable = false, updatable = false)
    })
    private ServiceBranch serviceBranches;
    
    @ManyToOne
    @JoinColumn(name = "supply_id", insertable = false, updatable = false)
    private Supply supply;
    
    @Column(nullable = false)
    @NotNull(message = "la cantidad no puede estar vacía")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private Integer quantity;
}