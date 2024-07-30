package com.example.Prueba.repositories;

import com.example.Prueba.models.Role;
import com.example.Prueba.models.userModel;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<userModel, Long> {
    @Query("SELECT u FROM userModel u WHERE u.role.name = :roleName")
    List<userModel> findByRoleName(@Param("roleName") String roleName);
}


