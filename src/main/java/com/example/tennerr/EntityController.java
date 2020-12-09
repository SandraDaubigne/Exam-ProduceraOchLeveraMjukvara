package com.example.tennerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntityController {

    @Autowired
    private EntityService entityService;

    @PostMapping("/user")
    public void createUser(@RequestBody User user){
        entityService.addUser(user);
    }
}
