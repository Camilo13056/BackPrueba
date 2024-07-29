package com.example.Prueba.controllers;

import com.example.Prueba.models.userModel;
import com.example.Prueba.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class userController {

    @Autowired
    private userService userService;

    @GetMapping
    public List<userModel> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public userModel saveUser(@RequestBody userModel user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public Optional<userModel> getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public userModel updateUser(@RequestBody userModel user, @PathVariable Long id) {
        return userService.updateById(user, id);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
