package com.enterprisemanager.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class SupplyService {
    @EmbeddedId
    private SupplyServiceId id;

    // @ManyToOne
    // @JoinColumn(name = "service_id",insertable=false, updatable=false)
    // private Service service;

    @ManyToOne
    @JoinColumn(name = "supply_id", insertable=false, updatable=false)
    private Supply supply;

    @Column(nullable = false)
    private int quantity;
}
