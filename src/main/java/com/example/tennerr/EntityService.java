package com.example.tennerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityService {

    @Autowired
    private EntityRepository entityRepository;

    //2:Skapa Funktion för att kunna registrera sig
    public void addWorker(Worker worker){
        entityRepository.save(worker);
    }

    //2:Skapa Funktion för att kunna registrera sig
    public void addWorkgiver(Workgiver workgiver){
        entityRepository.save(workgiver);
    }



}
