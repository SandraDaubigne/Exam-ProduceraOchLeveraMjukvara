package com.example.tennerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntityController {

    @Autowired
    private EntityService entityService;

    //2:Skapa Funktion för att kunna registrera sig
    @PostMapping("/worker")
    public void createWorker(@RequestBody Worker worker){
        entityService.addWorker(worker);
    }

    //2:Skapa Funktion för att kunna registrera sig
    @PostMapping("/workgiver")
    public void createWorkgiver(@RequestBody Workgiver workgiver){
        entityService.addWorkgiver(workgiver);
    }
}
