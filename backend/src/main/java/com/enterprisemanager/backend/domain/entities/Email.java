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

import lombok.Data;
@Entity
@Table(name = "email")
@Data
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = true)
    @NotEmpty(message = "El email no puede ser nulo")
    @jakarta.validation.constraints.Email(message = "Debe ser un email valido")
    private String mail;

    @ManyToOne
    @JoinColumn(nullable = false)
    private EmailType emailType;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Person person;
}
