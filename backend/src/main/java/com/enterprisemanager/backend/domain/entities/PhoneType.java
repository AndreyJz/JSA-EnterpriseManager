package com.enterprisemanager.backend.domain.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "phone_type")
@Data
public class PhoneType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede ser nulo")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$", message = "El campo solo puede contener letras y espacios, no caracteres especiales")
    @Column(length = 45, nullable = true)
    private String name;

    @OneToMany(mappedBy = "phoneType")
    @JsonIgnore
    private List<Phone> phones;
}




