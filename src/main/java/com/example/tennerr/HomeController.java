package com.example.tennerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private EntityService entityService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    //2:Skapa Funktion för att kunna registrera sig
    @PostMapping("/register")
    public void register(@RequestParam("username") String username, @RequestParam("password") String password) {

            entityService.registerWorker(username, password);

    }
}
