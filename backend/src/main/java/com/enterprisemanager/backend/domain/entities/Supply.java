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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Table(name = "supply")
@Data
public class Supply {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

    @Column(length = 45, nullable = false)
    @NotEmpty(message = "El código de barras no puede estar vacío")
    private String barcode;

    @Column(length = 45, nullable = false)
    @NotEmpty(message = "El nombre no puede estar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$", message = "El campo solo puede contener letras y espacios, no caracteres especiales")
    private String name;

    @Column(columnDefinition = "DECIMAL(16,2)", nullable = false)
    @NotEmpty(message = "El precio no puede estar vacío")
    @Positive(message = "El precio debe ser un número positivo")
    private float price;

    @Column(nullable = false)
    @NotEmpty(message = "El stock no puede estar vacío")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;

    @Column(nullable = false)
    @NotEmpty(message = "El stock minimo no puede estar vacío")
    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    private int stockMin;

    @Column(nullable = false)
    @NotEmpty(message = "El stock maximo no puede estar vacío")
    @Min(value = 0, message = "El stock máximo no puede ser negativo")
    private int stockMax;

    @OneToMany(mappedBy = "supply")
    @JsonIgnore
    private List<SupplyService> supplyService;

    @OneToMany(mappedBy = "supply")
    @JsonIgnore
    private List<PersonSupply> personSupply;
    
}
