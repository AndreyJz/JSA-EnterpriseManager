package com.enterprisemanager.backend.infrastructure.repositories.person;

import com.enterprisemanager.backend.domain.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.Person;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    boolean existsByUsername(String username);
    Optional<Person> findByUsername(String username);

    @Query("SELECT p FROM Person p WHERE p.role.name = 'ROLE_SUPPLIER'")
    List<Person> findSuppliers();

    @Query("SELECT p FROM Person p WHERE p.role.name = 'ROLE_CUSTOMER'")
    List<Person> findCustomers();

    @Query("SELECT p FROM Person p WHERE p.role.name = 'ROLE_EMPLOYEE'")
    List<Person> findEmployees();
}
