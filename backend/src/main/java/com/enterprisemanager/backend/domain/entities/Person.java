package com.enterprisemanager.backend.domain.entities;

import java.time.LocalDateTime;
import java.util.Collection;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;
import java.util.stream.Collectors;

//import com.enterprisemanager.backend.infrastructure.utils.enums.Role;+-
import com.fasterxml.jackson.annotation.JsonIgnore;

//import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "person")
@Data
public class Person implements UserDetails {

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

    @Column(nullable = false)
//    @NotEmpty(message = "la fecha de registro no puede ser nula")
    private LocalDateTime date;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(length = 500, nullable = false)
    @NotEmpty(message = "la contraseña no puede ser nula")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$", message = "La contraseña debe contener al menos una mayúscula, un número y un carácter especial, con una longitud mínima de 8 caracteres.")
        private String password;

    private String repeatedPassword;

    @ManyToOne
    @JoinColumn(name = "role_id")
//    private Role role = new Role("CUSTOMER");
    private Role role;

//    @ManyToMany
//    @JoinTable(
//            name = "people_roles",
//            joinColumns = @JoinColumn(name="person_id"),
//            inverseJoinColumns = @JoinColumn(name="role_id"),
//            uniqueConstraints = { @UniqueConstraint(columnNames = {"person_id",
//                    "role_id"})}
//    )
//    private Set<Role> roles;
//
//    @Transient
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private boolean admin = true;
//
//    private boolean enabled;

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
    @JoinColumn(nullable = true)
    private Branch branch;
//    public Person() {
//        roles = new HashSet<>();
//    }

//    @Enumerated(EnumType.STRING)
//    private Role role;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if(role == null) return null;
//
//        if(role.getPermissions() == null) return null;
//
//        return role.getPermissions().stream()
//                .map(each -> each.name())
//                .map(each -> new SimpleGrantedAuthority(each))
//                .collect(Collectors.toList());
//    }
//
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(role == null) return null;

        if(role.getPermissions() == null) return null;

        List<SimpleGrantedAuthority> authorities = role.getPermissions().stream()
                .map(each -> each.getOperation().getName())
                .map(each -> new SimpleGrantedAuthority(each))
//                .map(each -> {
//                    String permission = each.name();
//                    return new SimpleGrantedAuthority(permission);
//                })
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role.getName()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
