package com.example.Prueba.repositories;

import com.example.Prueba.models.userModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IUserRepository extends JpaRepository<userModel, Long> {
}
