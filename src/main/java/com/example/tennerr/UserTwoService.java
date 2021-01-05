package com.example.tennerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTwoService {

    @Autowired
    private UserTwoRepository userTwoRepository;

    public void saveUser(User user){
        userTwoRepository.save(user);
    }
}
