package com.enterprisemanager.backend.application.services;

import com.enterprisemanager.backend.domain.entities.Role;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findDefaultRole();
}
