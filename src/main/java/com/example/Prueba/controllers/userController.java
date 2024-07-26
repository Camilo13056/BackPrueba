package com.example.Prueba.controllers;

import com.example.Prueba.models.userModel;
import com.example.Prueba.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/users")

public class userController {

    @Autowired
    private userService userService;

    @GetMapping
    public ArrayList<userModel> getUsers(){
    return this.userService.getUsers();
    }

    @PostMapping
    public userModel saveUser(@RequestBody userModel user){
        return this.userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public Optional<userModel> getUserById(@PathVariable("id") Long id) {
        return this.userService.getById(id);
    }

    @PutMapping
    public userModel updateUserById(@RequestBody userModel request, Long id){
        return this.userService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.userService.deleteUser(id);

        if(ok){
            return "Usuario con id" + id + " eliminado";
        } else {
            return "Error";
        }
    }
    @PostMapping("/login")
    public String login(@RequestBody userModel loginRequest) {
        Optional<userModel> user = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (user.isPresent()) {
            return "Inicio de sesión exitoso";
        } else {
            return "Credenciales inválidas";
        }
    }
}

