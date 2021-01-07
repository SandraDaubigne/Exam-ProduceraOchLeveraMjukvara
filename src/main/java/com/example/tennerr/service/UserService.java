package com.example.tennerr.service;

import com.example.tennerr.entity.User;
import com.example.tennerr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Spara User
    public void saveUser(User user){
        userRepository.save(user);
    }

    //Hitta alla Users
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //Hitta user med användarnamm
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    //Hitta användare med id
    public User getUserById(long id){
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if(optional.isPresent()){
            user = optional.get();
        }else{
            throw new RuntimeException("User not found for id ::" + id);
        }
        return user;
    }
}

