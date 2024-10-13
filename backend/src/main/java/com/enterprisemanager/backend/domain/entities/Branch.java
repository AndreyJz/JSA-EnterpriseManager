package com.enterprisemanager.backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "branches")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    @NotNull(message = "Por favor agregue información")
    private String name;

    @ManyToOne
    private City cities;

    @ManyToOne
    private Company companies;

    @OneToMany(mappedBy = "branches")
    @JsonIgnore
    private Set<ServiceBranch> serviceBranches;

    @OneToMany(mappedBy = "branches")
    @JsonIgnore
    private Set<Person> people;
}
