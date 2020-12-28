package com.example.tennerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JobService jobService;

    //Startvyn
    @GetMapping("/")
    public String startPage(Model model){
        model.addAttribute("list", userService.getAllUsers());
        return "1login";
    }

    //Show Form register
    @GetMapping("/showNewUserForm")
    public String registerUser(Model model){
        UserEntity userEntity = new UserEntity();
        model.addAttribute("user", userEntity);
        return "2register";
    }

    //C - Create User - Register
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") UserEntity  userEntity){
        userService.saveUser(userEntity);
        return "1login";
    }


    // Login function
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, Model model){

        UserEntity user = userService.getUserByUsername(username);

        if(user !=null && username.equals(user.getUsername())){

                if(user.isWorker()){
                    List<Job> jobs = jobService.getAllJobs();
                    model.addAttribute("jobs", jobs );
                    model.addAttribute("user", user);
                    return "3startpageworker";

                }else if(user.isWorkgiver()){

                    model.addAttribute("user", user);
                    return "3startpageworkgiver";
                }

        }
        return "error";
    }

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


    //**********JOB***************//

    //Show Form registerJob
    @GetMapping("/registerJob")
    public String registerJob(Model model){
        Job job  = new Job();
        model.addAttribute("job", job);
        return "createJob";
    }

    //C - Create Job
    @PostMapping("/saveJob")
    public String saveJob(@ModelAttribute("job") Job job){
        jobService.saveJob(job);
        return "success";
    }

    //R - Read Show all jobs
    @GetMapping("/allJobs")
    public String allJobs(Model model){
        List<Job> jobs = jobService.getAllJobs();
        model.addAttribute("jobs", jobs );
        return "sealljobs";
    }





}
