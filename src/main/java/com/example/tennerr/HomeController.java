package com.example.tennerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private EntityService entityService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    //2:Skapa Funktion för att kunna registrera sig
    //Registrera arbetare
    @RequestMapping(value = "/createworker",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createWorker(@RequestBody Worker worker)
    {
        entityService.addWorker(worker);
        return "index";
    }

    //2:Skapa Funktion för att kunna registrera sig
    //registrera arbetsgivare
    @RequestMapping(value = "/createworkgiver",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createWorkgiver(@RequestBody Workgiver workgiver)
    {
        entityService.addWorkgiver(workgiver);
        return "index";
    }




}
