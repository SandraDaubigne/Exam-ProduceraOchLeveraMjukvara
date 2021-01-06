package com.example.tennerr.controller;

//Entity
import com.example.tennerr.entity.Job;
import com.example.tennerr.entity.User;

//Service
import com.example.tennerr.service.JobService;
import com.example.tennerr.service.UserService;

//Annotations
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


    /**************************VIEWS***********************************/

    //Startvyn
    @GetMapping("/")
    public String startPage() {
        return "1login";
    }


    /*************************REGISTER*************************************/
    //Renderar: 2Register.html
    //Show Form register
    @GetMapping("/showNewUserForm")
    public String registerUser(@ModelAttribute("user") User user) {

        return "2register";
    }

    //Register User
    //Denna behöver ha @Modelattribute user för att return 2 register finns här pga felmeddelandet
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user,
                           Model model,
                           @RequestParam("password") String password,
                           @RequestParam("newpassword") String newpassword) {

        if (password.equals(newpassword)) {
            System.out.println(password);
            userService.saveUser(user);

        } else if (!password.equals(newpassword)) {
            model.addAttribute("error", "Du har angett olika lösenord, var vänlig försök igen!");
            return "2register";
        }

        return "redirect:/";
    }


    /*************************LOGIN*************************************/
    //Renderar: 3startpageworker
    @GetMapping("/loginWorker/{id}")
    public String loginWorker(
            @ModelAttribute("user") User user,
            @ModelAttribute("job") Job job,
            Model model) {

        model.addAttribute("jobs", jobService.getAllJobs());
        return "3startpageworker";
    }

    //Renderar: 3startpageworkgiver
    @GetMapping("/loginWorkgiver/{id}")
    public String loginWorkgiver(@ModelAttribute("user") User userEntity,
                                 @ModelAttribute("job") Job job,
                                 Model model) {
        model.addAttribute("jobs", jobService.getAllJobs());
        return "3startpageworkgiver";
    }

    //Login User
    @PostMapping("login")
    public String login(@RequestParam("username") String username) {

        User user = userService.getUserByUsername(username);
        Long id = user.getId();

        if (user != null && username.equals(user.getUsername())) {

            if (user.getRoles().isWorker()) {
                return "redirect:/loginWorker/" + id;

            } else if (user.getRoles().isWorkgiver()) {
                return "redirect:/loginWorkgiver/" + id;
            }
        }
        return "error";
    }


    /*************************CREATE JOB*************************************/
    //Renderar: CreateJob.html
    @GetMapping("/showFormRegisterJob/{id}")
    public String showFormRegisterJob(@ModelAttribute("user") User user,
                                      @ModelAttribute("job") Job job,
                                      @PathVariable(value = "id") long id,
                                      Model model) {

        return "createJob";
    }

    //C - Create Job
    @PostMapping("/saveJob/{id}")
    public String createJob(@ModelAttribute("job")Job job,
                            @PathVariable(value = "id") long id,
                            Model model) {

        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        job.setUser(user);
        jobService.saveJob(job);

        return "redirect:/loginWorkgiver/" + id;
    }


    /*************************UPDATE*************************************/
    //Renderar: 4Profileworker och 4profileworkgiver
    @GetMapping("/showformforupdate/{id}")
    public String showFormForUpdate(
                                        @PathVariable(value = "id") long id,
                                    Model model) {

        List<Job> jobs = jobService.findAllJobs(id);
        model.addAttribute("jobs", jobs);

        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        if (user.getRoles().isWorker()) {
            model.addAttribute("user", user);
            return "4profileworker";

        } else if (user.getRoles().isWorkgiver()) {

            model.addAttribute("user", user);
            return "4profileworkgiver";
        }
        return "error";
    }

    //U - Update User
    @PostMapping("/updateUser/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable(value = "id") long id,
                             Model model) {

        List<Job> jobs = jobService.findAllJobs(id);
        model.addAttribute("jobs", jobs);

        userService.saveUser(user);

        User users = userService.getUserById(id);
        model.addAttribute("user", user);

        //String message = "Dina uppgifter har ändrats!";
        //model.addAttribute("msg", message);

        if (users.getRoles().isWorker()) {
            model.addAttribute("user", user);
            return "4profileworker";

        } else if (users.getRoles().isWorkgiver()) {

            model.addAttribute("user", user);
            return "4profileworkgiver";
        }

        return "error";
    }

    /*************************PUBLIC VIEW************************************/
    //Renderar: 5publicWorker and 5publicworkgiver
    // Show page with public view
    @GetMapping("/showPublicView/{id}")
    public String showPublicView(@PathVariable(value = "id") long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        if (user.getRoles().isWorker()) {
            model.addAttribute("user", user);
            return "5publicWorker";

        } else if (user.getRoles().isWorkgiver()) {

            model.addAttribute("user", user);
            return "5publicworkgiver";
        }
        return "error";
    }



































    /*************************GAMMALT*************************************/
    /************NY HITTA ALLA JOBB SOM DU HAR SKAPAT***********/
    @GetMapping("/showAllJobs/{id}")
    public String showAllJobs(@ModelAttribute("job" )Job job,
                              @PathVariable(value = "id") long id,
                              Model model) {

        List<Job> jobs= jobService.findAllJobs(id);
        model.addAttribute("jobs", jobs);

        /*
        if (user.getRolesCategory().isWorker()) {
            model.addAttribute("user", user);
            return "4profileworker";

        } else if (user.getRolesCategory().isWorkgiver()) {

            model.addAttribute("user", user);
            return "4profileworkgiver";
        }*/

        return "findjob";
    }



}
