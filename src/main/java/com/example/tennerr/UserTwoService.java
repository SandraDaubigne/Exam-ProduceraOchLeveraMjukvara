package com.example.tennerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserTwoService {

    @Autowired
    private UserTwoRepository userTwoRepository;

    public User getUserByUsername(String username){
        return userTwoRepository.findByUsername(username);
    }

    public void saveUser(User user){
        userTwoRepository.save(user);
    }

    public User getUserById(long id){
        Optional<User> optional = userTwoRepository.findById(id);
        User user = null;
        if(optional.isPresent()){
            user = optional.get();
        }else{
            throw new RuntimeException("User not found for id ::" + id);
        }
        return user;
    }
}
