package com.enterprisemanager.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "phone")
@Data
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "el numero no debe ser nulo")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Debe proporcionar un número de teléfono válido")
    @Column(nullable = false)
    private int number;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PhoneType phoneType;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Person person;

}
