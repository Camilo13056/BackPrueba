package com.example.Prueba.services;

import com.example.Prueba.models.taskModel;
import com.example.Prueba.repositories.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class taskService {

    @Autowired
    private ITaskRepository taskRepository;

    public List<taskModel> getTasks() {
        return taskRepository.findAll();
    }

    public taskModel saveTask(taskModel task) {
        return taskRepository.save(task);
    }

    public Optional<taskModel> getById(Long id) {
        return taskRepository.findById(id);
    }

    public taskModel updateById(taskModel task, Long id) {
        Optional<taskModel> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            task.setId(id);
            return taskRepository.save(task);
        } else {
            throw new RuntimeException("Task not found");
        }
    }

    public Boolean deleteTask(Long id) {
        Optional<taskModel> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            taskRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
