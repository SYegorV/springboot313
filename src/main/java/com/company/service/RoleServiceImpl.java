package com.company.service;

import com.company.models.Role;
import com.company.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void addRole(Role role) {
        roleRepository.saveAndFlush(role);
    }

    @Override
    public Role roleByID(Long id) {
        Optional<Role> roleById = roleRepository.findById(id);
        if (roleById.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Role with id %s not found", id));
        } else {
            return roleById.get();
        }
    }
}
