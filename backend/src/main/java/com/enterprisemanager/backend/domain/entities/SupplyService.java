package com.enterprisemanager.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Entity
@Table(name = "supply_service") 
@Data
public class SupplyService {
    @EmbeddedId
    private SupplyServiceId id;

    @ManyToOne
    private ServiceBranch serviceBranches;

    @ManyToOne
    @JoinColumn(name = "supply_id", insertable=false, updatable=false)
    private Supply supply;

    @Column(nullable = false)
    @NotEmpty(message = "la cantidad no puede estar vacío")
    @Min(value = 0, message = "La cantidad no puede ser negativo")
    private int quantity;

    
}
