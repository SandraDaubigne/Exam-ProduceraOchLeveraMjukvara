package com.example.tennerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JobService jobService;

    /**************************VIEWS***********************************/

    //Startvyn
    @GetMapping("/")
    public String startPage(){
        //model.addAttribute("list", userService.getAllUsers());
        return "1login";
    }

    //Renderar: 2Register.html
    //Show Form register
    @GetMapping("/showNewUserForm")
    public String registerUser(@ModelAttribute("user") UserEntity user){
        return "2register";
    }

    //Renderar: CreateJob.html
    @GetMapping("/showFormRegisterJob")
    public String registerJob(@ModelAttribute("job") Job job){
        return "createJob";
    }

    /*************************FUNCTIONS*************************************/

    //*********************REGISTER**************************//
    //Denna behöver ha @Modelattribute user för att return 2 register finns här pga felmeddelandet
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") UserEntity userEntity,
                           Model model,
                           @RequestParam("password") String password,
                           @RequestParam("newpassword") String newpassword){

        if(password.equals(newpassword)){
            System.out.println(password);
            userService.saveUser(userEntity);

        }else if(!password.equals(newpassword)){
            model.addAttribute("error", "Du har angett olika lösenord, var vänlig försök igen!");
            return "2register";
        }

        return "redirect:/";
    }

    //*********************CREATE JOB**************************//
    //C - Create Job
    @PostMapping("/saveJob")
    public String saveJob(Job job){
        jobService.saveJob(job);
        return "success";
    }


    //*********************LOGIN***************************//

    //view visa 3startpageworker
    @GetMapping("/loginWorker/{id}")
    public String loginWorker(@ModelAttribute("user") UserEntity userEntity,
                              @ModelAttribute("job") Job job,
                              Model model)
    {
        model.addAttribute("jobs", jobService.getAllJobs() );
        return "3startpageworker";
    }

    @GetMapping("/loginWorkgiver/{id}")
    public String loginWorkgiver(@ModelAttribute("user") UserEntity userEntity,
                                 @ModelAttribute("job") Job job,
                                 Model model)
    {
        model.addAttribute("jobs", jobService.getAllJobs() );
        return "3startpageworkgiver";
    }



    //wiew visa 3startpageworkgiver

    //tar emot post
    //hitta användare
    //skicka vidare till rätt vy, redirect + url + id

    //Get förser sidan med model
    @PostMapping("login")
    public String login(@RequestParam("username") String username){

        UserEntity user = userService.getUserByUsername(username);
        Long id = user.getId();

        if(user !=null && username.equals(user.getUsername())){

            if(user.isWorker()){

                //Urlen kommer att vara /login fortfarande även om den skickas vidare.
                return "redirect:/loginWorker/" + id;

            }else if(user.isWorkgiver()){

                //Urlen kommer att vara /login fortfarande även om den skickas vidare.
                return "redirect:/loginWorkgiver/" + id;
            }

        }
        return "error";
    }

    // Login function
    /*
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, ModelMap model){

        UserEntity user = userService.getUserByUsername(username);
        model.addAttribute("jobs", jobService.getAllJobs() );

        if(user !=null && username.equals(user.getUsername())){

                if(user.isWorker()){

                    model.addAttribute("user", user);
                    return "3startpageworker";

                }else if(user.isWorkgiver()){

                    model.addAttribute("user", user);
                    return "3startpageworkgiver";
                }

        }
        return "error";
    }*/

    //*********************UPDATE***************************//
    //Show form for update user
    @GetMapping("/showformforupdate/{id}")
    public String showFormForUpdate(@PathVariable (value= "id") long id, Model model){
        UserEntity user = userService.getUserById(id);
        model.addAttribute("user", user);

        if(user.isWorker()){
            model.addAttribute("user", user);
            return "4profileworker";

        }else if(user.isWorkgiver()){

            model.addAttribute("user", user);
            return "4profileworkgiver";
        }
        return "error";
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
            return "4profileworker";

        }else if(user.isWorkgiver()){

            model.addAttribute("user", user);
            return "4profileworkgiver";
        }

        return "error";
    }

    //*********************SHOW PUBLIC VIEW***************************//
    //Show form for update user
    @GetMapping("/showPublicView/{id}")
    public String showPublicView(@PathVariable (value= "id") long id, Model model){
        UserEntity user = userService.getUserById(id);
        model.addAttribute("user", user);

        if(user.isWorker()){
            model.addAttribute("user", user);
            return "5publicWorker";

        }else if(user.isWorkgiver()){

            model.addAttribute("user", user);
            return "5publicworkgiver";
        }
        return "error";
    }




}
