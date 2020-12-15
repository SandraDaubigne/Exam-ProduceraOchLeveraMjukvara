package com.example.tennerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntityController {

    @Autowired
    private EntityService entityService;

    /*
    @RequestMapping(value = "/login/{username}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User loginUserByUsername(@PathVariable String username){

        return entityService.getUserByUsername(username);
        //return "userloggedin";

    }*/

}
