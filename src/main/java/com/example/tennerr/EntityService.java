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


    public Worker registerWorker(String username, String password){

        Worker worker = new Worker();
        worker.setUsername(username);
        worker.setPassword(password);

        return entityRepository.save(worker);

    }

    //2:Skapa Funktion för att kunna registrera sig
    public Workgiver registerWorkgiver(String username, String password){

        Workgiver workgiver = new Workgiver();
        workgiver.setAddress(username);
        workgiver.setPassword(password);

        return entityRepository.save(workgiver);

    }

}
