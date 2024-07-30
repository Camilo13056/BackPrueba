package com.example.Prueba.controllers;

import com.example.Prueba.models.taskModel;
import com.example.Prueba.services.taskService;
import com.example.Prueba.models.userModel;
import com.example.Prueba.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class taskController {

    @Autowired
    private taskService taskService;

    @Autowired
    private userService userService;

    @GetMapping
    public ResponseEntity<List<taskModel>> getTasks() {
        try {
            List<taskModel> tasks = taskService.getTasks();
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<taskModel> saveTask(@RequestBody taskModel task) {
        try {
            Optional<userModel> user = userService.getById(task.getUser().getId());
            if (user.isPresent()) {
                task.setUser(user.get());
                taskModel savedTask = taskService.saveTask(task);
                return ResponseEntity.ok(savedTask);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<taskModel> getTaskById(@PathVariable Long id) {
        Optional<taskModel> task = taskService.getById(id);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<taskModel> updateTask(@RequestBody taskModel task, @PathVariable Long id) {
        try {
            Optional<userModel> user = userService.getById(task.getUser().getId());
            if (user.isPresent()) {
                task.setUser(user.get());
                taskModel updatedTask = taskService.updateById(task, id);
                return ResponseEntity.ok(updatedTask);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable Long id) {
        try {
            Boolean isDeleted = taskService.deleteTask(id);
            if (isDeleted) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
}
