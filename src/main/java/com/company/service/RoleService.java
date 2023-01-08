package com.company.service;

import com.company.models.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();
    void addRole(Role role);
    Role roleByID(Long id);
}
