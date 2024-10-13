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
import lombok.Data;

@Entity
@Table(name = "person")
@Data
public class Person {

    @Id
    private String id;

    @Column(length = 45, nullable = false)
        private String name;

    @Column(length = 45, nullable = false)
        private String lastname;

    @Column(length = 45, nullable = false)
        private String date;

    @Column(length = 45, nullable = false)
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
    private Branch branches;

}
