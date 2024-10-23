package com.enterprisemanager.backend.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "operations")
@Data
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "You have to add a name")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "You have to add a path")
    private String path;

    @Column(nullable = false)
    @NotNull(message = "You have to add a HTTP Method")
    private String httpMethod;

    @Column(nullable = false)
    private boolean permitAll;

    @ManyToOne
    @JoinColumn(name = "module_id")
    @NotNull(message = "You have to add a module")
    private Module module;
}
