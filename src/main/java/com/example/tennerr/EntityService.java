package com.example.tennerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityService {

    @Autowired
    private EntityRepository entityRepository;

    public void addUser(User user){
        entityRepository.save(user);
    }

}
