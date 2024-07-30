package com.example.Prueba.repositories;

import com.example.Prueba.models.taskModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<taskModel, Long> {
}
