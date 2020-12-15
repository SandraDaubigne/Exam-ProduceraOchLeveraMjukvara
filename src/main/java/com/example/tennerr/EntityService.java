package com.example.tennerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityService {

    @Autowired
    private EntityRepository entityRepository;

    //2:Skapa Funktion för att kunna registrera sig
    public void createUser(User user){
        entityRepository.save(user);
    }

    //Skapa funktion för att kunna logga in




}
