package com.enterprisemanager.backend.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "granted_permissions")
@Data
public class GrantedPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @NotNull(message = "You have to add a role")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "operation_id")
    @NotNull(message = "You have to add a operation")
    private Operation operation;
}
