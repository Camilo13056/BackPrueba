// src/main/java/com/example/Prueba/repositories/IRoleRepository.java

package com.example.Prueba.repositories;

import com.example.Prueba.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
