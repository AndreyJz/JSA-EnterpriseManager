package com.enterprisemanager.backend.domain.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceBranchPk {
    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "service_id")
    private Long serviceId;
}
