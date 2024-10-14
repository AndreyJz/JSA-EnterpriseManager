package com.enterprisemanager.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "person_supply") 
@Data
public class PersonSupply {

    @EmbeddedId
    private PersonSupplyId id;

    @ManyToOne
    @JoinColumn(name = "person_id",insertable=false, updatable=false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "supply_id", insertable=false, updatable=false)
    private Supply supply;

    @Column(nullable = false)
    @NotNull(message = "la cantidad no puede estar vacío")
    @Min(value = 0, message = "La cantidad no puede ser negativo")
    private int quantity;
}
