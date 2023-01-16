package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AccessService {
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void doAdminStuff() {
    }
}
