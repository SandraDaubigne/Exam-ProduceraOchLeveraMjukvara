package com.example.tennerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private EntityService entityService;

    @GetMapping("/")
    public String home(){
        return "register";
    }

    //2: Skapa funktion för att kunna logga in
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, Model model, User user){
        User use = entityService.getUserByUsername(username);

        if(use !=null && username.equals(use.getUsername())){
            model.addAttribute("name", user.getUsername());
            return "userloggedin";
        }else if(use !=null && username.equals(use.getUsername())){
            return "register";
        }
        return "register";
    }


    //2: Skapa funktion för att kunna registrera sig.
    //RequestBody för att kunna ta emot JSON
    @RequestMapping(value = "/createuser",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createUser(@RequestBody User user){

        entityService.createUser(user);
        return "register";
    }


}
