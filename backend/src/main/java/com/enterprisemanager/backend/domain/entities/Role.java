//package com.enterprisemanager.backend.domain.entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.Table;
//import lombok.Data;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
////@Table(name = "roles")
//@Data
//public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(unique = true)
//    private String name;
//
////    @JsonIgnoreProperties({"roles", "handler", "hibernateLazyInitializer"})
////    @ManyToMany(mappedBy = "roles")
////    private List<Person> users;
////
////    public Role() {
////        this.users = new ArrayList<>();
////    }
//}
