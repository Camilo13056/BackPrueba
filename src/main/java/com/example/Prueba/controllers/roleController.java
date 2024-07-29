// src/main/java/com/example/Prueba/controllers/RoleController.java

package com.example.Prueba.controllers;

import com.example.Prueba.models.Role;
import com.example.Prueba.services.roleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "*")
public class roleController {

    @Autowired
    private roleService roleService;

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Optional<Role> getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @DeleteMapping("/{id}")
    public boolean deleteRole(@PathVariable Long id) {
        return roleService.deleteRole(id);
    }
}
