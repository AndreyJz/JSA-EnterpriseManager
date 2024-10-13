package com.enterprisemanager.backend.domain.entities;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
@AllArgsConstructor
public class PersonSupplyId implements Serializable {
    @Column(name = "person_id")
    private String personId;

    @Column(name = "supply_id")
    private Long supplyId;

    public PersonSupplyId() {}

}

