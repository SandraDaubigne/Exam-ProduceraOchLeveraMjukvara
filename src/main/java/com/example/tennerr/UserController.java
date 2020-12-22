package com.example.tennerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //Startvyn
    @GetMapping("/")
    public String startPage(Model model){
        model.addAttribute("list", userService.getAllUsers());
        return "userstart";
    }

    //Show Form register
    @GetMapping("/showNewUserForm")
    public String registerUser(Model model){
        UserEntity userEntity = new UserEntity();
        model.addAttribute("user", userEntity);
        return "userform";
    }

    //C - Create User
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") UserEntity  userEntity){
        userService.saveUser(userEntity);
        return "registerfeedback";
    }

    //Show Login Form
    @GetMapping("/showLoginForm")
    public String showLoginForm(){
        return "userstart";
    }


}
