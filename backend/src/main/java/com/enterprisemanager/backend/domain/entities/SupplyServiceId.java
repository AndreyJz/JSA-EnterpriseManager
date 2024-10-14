package com.enterprisemanager.backend.domain.entities;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplyServiceId implements Serializable {
    @Column(name = "service_branches_branch_id")
    private Long branchId;
    @Column(name = "service_branches_service_id")
    private Long serviceId;
    @Column(name = "supply_id")
    private Long supplyId;
}

