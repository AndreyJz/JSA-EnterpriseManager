package com.enterprisemanager.backend.application.services;

import com.enterprisemanager.backend.domain.entities.Role;
import com.enterprisemanager.backend.domain.entities.ServiceApproval;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    Optional<Role> findDefaultRole();
    List<Role> findAll();
}
