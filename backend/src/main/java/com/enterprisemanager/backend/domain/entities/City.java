package com.enterprisemanager.backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = true)
    @NotNull(message = "Por favor agregue información")
    private String name;

    @ManyToOne
    private Region regions;

    @OneToMany(mappedBy = "cities")
    @JsonIgnore
    private List<Branch> branches;
}
