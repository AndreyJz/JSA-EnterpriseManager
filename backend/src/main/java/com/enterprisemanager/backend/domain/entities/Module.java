package com.enterprisemanager.backend.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "modules")
@Data
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "You have to add a name")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "You have to add a basePath")
    private String basePath;
}
