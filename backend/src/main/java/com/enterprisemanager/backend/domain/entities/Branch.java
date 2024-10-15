package com.enterprisemanager.backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$", message = "El campo solo puede contener letras y espacios, no caracteres especiales")
    @NotNull(message = "You have to add a name")
    private String name;

    @Column(length = 20, nullable = false)
    @NotNull(message = "You have to add a nit")
    private String nit;

    @Column(length = 20, nullable = false)
//    @NotNull(message = "You have to add a date")
    private LocalDateTime creationDate;

    @ManyToOne
    @NotNull(message = "You have to add a city")
    private City city;

    @ManyToOne
    @NotNull(message = "You have to add a company")
    private Company company;

    @OneToMany(mappedBy = "branch")
    @JsonIgnore
    private Set<ServiceBranch> serviceBranch;

    @OneToMany(mappedBy = "branch")
    @JsonIgnore
    private Set<Person> person;
}
