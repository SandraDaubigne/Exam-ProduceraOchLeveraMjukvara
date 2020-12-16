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
    public String login(@RequestParam("username") String username, Model model){

        User user = entityService.getUserByUsername(username);

        model.addAttribute("user", user);

        return "userloggedin";
        /*

        if(user !=null && username.equals(user.getUsername())){
            if(user.getRole().equals("worker")){
                model.addAttribute("name", user.getUsername());
                model.addAttribute("role", user.getRole());
                return "profileWorker";
            }else if(user.getRole() == "workgiver"){
                model.addAttribute("name", user.getUsername());
                return "Workgiver";
            }
            return "register";

        }else if(user !=null && username.equals(user.getUsername())){
            return "register";
        }
        return "register";*/
    }


    //2: Skapa funktion för att kunna registrera sig.
    //RequestBody för att kunna ta emot JSON
    @RequestMapping(value = "/createuser",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createUser(@RequestBody User user){

        entityService.createUser(user);
        return "register";
    }

    //@ResponseBody för att kunna skicka tillbaka JSON
    @RequestMapping(value = "/login/{username}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User loginUserByUsername(@PathVariable String username, User user){

        return entityService.getUserByUsername(username);
        //return "userloggedin";
    }




}
