package com.example.tennerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private EntityService entityService;

    @GetMapping("/")
    public String home(){
        return "index";
    }
}
