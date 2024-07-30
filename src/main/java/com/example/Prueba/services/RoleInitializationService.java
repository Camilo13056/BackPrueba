package com.example.Prueba.services;

import com.example.Prueba.models.Role;
import com.example.Prueba.repositories.IRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleInitializationService {

    @Autowired
    private IRoleRepository roleRepository;

    @PostConstruct
    public void initializeRoles() {
        List<String> roles = Arrays.asList("Usuario", "Administrador", "SuperAdmin");

        for (String roleName : roles) {
            if (!roleRepository.findByName(roleName).isPresent()) {
                Role role = new Role();
                role.setName(roleName);
                roleRepository.save(role);
            }
        }
    }
}
