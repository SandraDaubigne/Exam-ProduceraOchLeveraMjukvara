package com.example.tennerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public void saveUser(UserEntity userEntity){
        userRepository.save(userEntity);
    }

    public UserEntity getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public UserEntity getUserById(long id){
        Optional<UserEntity> optional = userRepository.findById(id);
        UserEntity user = null;
        if(optional.isPresent()){
            user = optional.get();
        }else{
            throw new RuntimeException("User not found for id ::" + id);
        }
        return user;
    }
}
