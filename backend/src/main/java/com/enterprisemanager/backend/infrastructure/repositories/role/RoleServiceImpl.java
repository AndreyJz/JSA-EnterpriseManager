package com.enterprisemanager.backend.infrastructure.repositories.role;

import com.enterprisemanager.backend.application.services.IRoleService;
import com.enterprisemanager.backend.domain.entities.Country;
import com.enterprisemanager.backend.domain.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Value("${security.default.role}")
    private String defaultRole;

    @Override
    public Optional<Role> findDefaultRole() {
        return roleRepository.findByName(defaultRole);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
     return (List<Role>) roleRepository.findAll();
    }
}
