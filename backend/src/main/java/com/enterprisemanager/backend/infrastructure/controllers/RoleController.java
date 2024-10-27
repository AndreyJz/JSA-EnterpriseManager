package com.enterprisemanager.backend.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprisemanager.backend.application.services.ICountryService;
import com.enterprisemanager.backend.application.services.IRoleService;
import com.enterprisemanager.backend.domain.entities.Country;
import com.enterprisemanager.backend.domain.entities.Role;

@RestController
@RequestMapping("/api/Roles")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @GetMapping
    public List<Role> getAll() { return roleService.findAll(); }
}
