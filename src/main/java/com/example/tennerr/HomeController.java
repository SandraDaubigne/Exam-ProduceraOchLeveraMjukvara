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
        return "register";
    }

    @RequestMapping(value = "/createuser",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createUser(@RequestBody User user){

        entityService.createUser(user);
        return "register";
    }

    @RequestMapping(value = "/login/{username}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User loginUserByUsername(@PathVariable String username){

        return entityService.getUserByUsername(username);
        //return "userloggedin";

    }








}
