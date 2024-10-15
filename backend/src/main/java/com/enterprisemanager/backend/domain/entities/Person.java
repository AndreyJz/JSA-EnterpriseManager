package com.enterprisemanager.backend.domain.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "person")
@Data
public class Person {

    @Id
    private String id;

    @Column(length = 45, nullable = false)
    @NotEmpty(message = "El nombre no puede ser nulo")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$", message = "El campo solo puede contener letras y espacios, no caracteres especiales")
        private String name;

    @Column(length = 45, nullable = false)
    @NotEmpty(message = "El apellido no puede ser nula")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$", message = "El campo solo puede contener letras y espacios, no caracteres especiales")
        private String lastname;

    @Column(columnDefinition = "datetime", nullable = false)
    @NotEmpty(message = "la fecha de registro no puede ser nula")
        private String date;

    @Column(length = 45, nullable = false)
    @NotEmpty(message = "la contraseña no puede ser nula")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$", message = "La contraseña debe contener al menos una mayúscula, un número y un carácter especial, con una longitud mínima de 8 caracteres.")
        private String password;

    @OneToMany(mappedBy = "person")
    @JsonIgnore
    private List<Phone> phone;

    @OneToMany(mappedBy = "person")
    @JsonIgnore
    private List<Email> email;
    
    @OneToMany(mappedBy = "person")
    @JsonIgnore
    private List<PersonSupply> personSupply;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<ServiceOrder> customer;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<ServiceOrder> employee;

    @OneToMany(mappedBy = "person")
    @JsonIgnore
    private List<WorkOrderDetail> workOrderDetail;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PersonType personType;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Branch branch;

}
