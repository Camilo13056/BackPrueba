package com.example.Prueba.services;

import com.example.Prueba.models.Role;
import com.example.Prueba.models.userModel;
import com.example.Prueba.repositories.IUserRepository;
import com.example.Prueba.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class userService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IRoleRepository roleRepository;

    public ArrayList<userModel> getUsers() {
        return (ArrayList<userModel>) userRepository.findAll();
    }

    public List<userModel> getUsersByRole(String roleName) {
        return userRepository.findByRoleName(roleName);
    }

    public userModel saveUser(userModel user) {
        if (user.getRole() == null) {
            Optional<Role> userRole = roleRepository.findByName("Usuario");
            if (userRole.isPresent()) {
                user.setRole(userRole.get());
            } else {
                throw new RuntimeException("Rol no encontrado");
            }
        }
        return userRepository.save(user);
    }

    public Optional<userModel> getById(Long id) {
        return userRepository.findById(id);
    }

    public userModel updateById(userModel request, Long id) {
        Optional<userModel> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isPresent()) {
            userModel user = existingUserOptional.get();
            user.setName(request.getName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setAddress(request.getAddress());
            user.setPhone(request.getPhone());
            user.setPassword(request.getPassword());
            user.setStatus(request.isStatus());

            if (request.getRole() != null && request.getRole().getId() != null) {
                Optional<Role> roleOptional = roleRepository.findById(request.getRole().getId());
                if (roleOptional.isPresent()) {
                    user.setRole(roleOptional.get());
                    System.out.println("Rol actualizado a ID: " + request.getRole().getId());
                } else {
                    throw new RuntimeException("Rol no encontrado");
                }
            }

            // Debug: Verificar los valores antes de guardar
            System.out.println("Guardando usuario: " + user);

            return userRepository.save(user);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    public Boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<userModel> authenticateUser(String email, String password) {
        return userRepository.findAll().stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst();
    }
}
