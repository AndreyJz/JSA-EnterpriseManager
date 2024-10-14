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
@Table(name = "person_type")
@Data
public class PersonType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = true)
    @NotEmpty(message = "El nombre no puede ser nulo")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$", message = "El campo solo puede contener letras y espacios, no caracteres especiales")
    private String name;

    @OneToMany(mappedBy = "personType")
    @JsonIgnore
    private List<Person> person;
    
}