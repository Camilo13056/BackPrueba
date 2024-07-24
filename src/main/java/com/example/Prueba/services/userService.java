package com.example.Prueba.services;

import com.example.Prueba.models.userModel;
import com.example.Prueba.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service

public class userService {

    @Autowired
    IUserRepository userRepository;

     public ArrayList<userModel> getUsers(){
                return (ArrayList<userModel>) userRepository.findAll();
            }

            public userModel saveUser(userModel user){
                return userRepository.save(user);
            }

            public Optional<userModel> getById(Long id){
                return userRepository.findById(id);
            }

            public userModel updateById(userModel request, Long id) {
                userModel user = userRepository.findById(id).get();

                user.setName(request.getName());
                user.setLastName(request.getLastName());
                user.setEmail(request.getEmail());
                user.setAddress(request.getAddress());
                user.setPhone(request.getPhone());
                user.setPassword(request.getPassword());
                user.setStatus(request.isStatus());

                return user;

            }

            public Boolean deleteUser (Long id){
                try{
                    userRepository.deleteById(id);
                    return true;
                }catch (Exception e){
                    return false;
                }
            }
}
