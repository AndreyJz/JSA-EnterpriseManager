package com.enterprisemanager.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    private int quantity;
}
