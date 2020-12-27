package com.example.tennerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //Startvyn
    @GetMapping("/")
    public String startPage(Model model){
        model.addAttribute("list", userService.getAllUsers());
        return "1login";
        //return "userstart";
    }

    //Show Form register
    @GetMapping("/showNewUserForm")
    public String registerUser(Model model){
        UserEntity userEntity = new UserEntity();
        model.addAttribute("user", userEntity);
        return "2register";
        //return "userform";
    }

    //C - Create User
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") UserEntity  userEntity){
        userService.saveUser(userEntity);
        return "1login";
    }

    //Show Login Form
    @GetMapping("/showLoginForm")
    public String showLoginForm(){
        return "userstart";
    }

    //Show form for update user
    @GetMapping("/showformforupdate/{id}")
    public String showFormForUpdate(@PathVariable (value= "id") long id, Model model){
        UserEntity user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "userprofile";
    }

    //U - Update User
    //fortsätta här, nu används saveUser metoden här ovan
    @PostMapping("/updateUser/{id}")
    public String updateUser(@ModelAttribute("user") UserEntity userEntity,
                             @PathVariable (value= "id") long id,
                             Model model){
        userService.saveUser(userEntity);
        UserEntity user = userService.getUserById(id);
        model.addAttribute("user", user);
        String message = "Dina uppgifter har ändrats!";
        model.addAttribute("msg", message);

        if(user.isWorker()){
            model.addAttribute("user", user);
            return "3startpageworker";

        }else if(user.isWorkgiver()){

            model.addAttribute("user", user);
            return "3startpageworkgiver";
        }

        return "error";
    }

    // Login function
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, Model model){

        UserEntity user = userService.getUserByUsername(username);

        if(user !=null && username.equals(user.getUsername())){

                if(user.isWorker()){
                    model.addAttribute("user", user);
                    return "3startpageworker";

                }else if(user.isWorkgiver()){

                    model.addAttribute("user", user);
                    return "3startpageworkgiver";
                }

            model.addAttribute("user", user);
            return "userloggedin";
        }
        return "error";
    }




}
