/*
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
        return "login";
    }



    @PostMapping("/login")
    public String login(@RequestParam("username") String username, Model model){

        User user = entityService.getUserByUsername(username);

        if(user !=null && username.equals(user.getUsername())){
            if(user.getRole().equals("worker")){

                model.addAttribute("user", user);
                return "startpageworker";

            }else if(user.getRole().equals("workgiver")){

                model.addAttribute("user", user);
                return "startpageworkgiver";
            }
        }
        return "error";
    }

    @GetMapping("/register")
    public String registerpage(){
        return "register";
    }


    @RequestMapping(value = "/createuser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createUser(@RequestBody User user){

        entityService.createUser(user);

        return "register";
    }



    @GetMapping("privateprofile")
    public String privateProfile(){

        return "privateworker";
    }

    @GetMapping("publicprofile")
    public String publicProfile(){

        return "publicworker";
    }

}
*/