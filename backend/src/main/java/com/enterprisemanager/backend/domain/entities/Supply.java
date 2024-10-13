package com.enterprisemanager.backend.domain.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "supply")
@Data
public class Supply {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

    @Column(length = 45, nullable = true)
        private String barcode;

    @Column(length = 45, nullable = true)
        private String name;

    @Column( columnDefinition = "DECIMAL(16,2)", nullable = true)
        private float price;

    @Column(nullable = true)
        private int stock;

    @Column(nullable = true)
        private int stockMin;

    @Column(nullable = true)
        private int stockMax;

    @OneToMany(mappedBy = "supply")
    @JsonIgnore
    private List<SupplyService> supplyService;

    @OneToMany(mappedBy = "supply")
    @JsonIgnore
    private List<PersonSupply> personSupply;
    
}
