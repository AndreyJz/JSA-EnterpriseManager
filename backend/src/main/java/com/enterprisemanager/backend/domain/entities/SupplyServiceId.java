package com.enterprisemanager.backend.domain.entities;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
@AllArgsConstructor
public class SupplyServiceId implements Serializable{
    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "supply_id")
    private Long supplyId;

    public SupplyServiceId() {}
}
