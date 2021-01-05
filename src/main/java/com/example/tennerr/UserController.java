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

    @Autowired
    private UserTwoService userTwoService;

    /**************************VIEWS***********************************/

    //Startvyn
    @GetMapping("/")
    public String startPage() {
        return "1login";
    }





    //Renderar: 3startpageworker
    @GetMapping("/loginWorker/{id}")
    public String loginWorker(
            @ModelAttribute("user") UserEntity user,
            @ModelAttribute("job") Job job,
            Model model) {

        model.addAttribute("jobs", jobService.getAllJobs());
        return "3startpageworker";
    }

    //Renderar: 3startpageworkgiver
    @GetMapping("/loginWorkgiver/{id}")
    public String loginWorkgiver(@ModelAttribute("user") UserEntity userEntity,
                                 @ModelAttribute("job") Job job,
                                 Model model) {
        model.addAttribute("jobs", jobService.getAllJobs());
        return "3startpageworkgiver";
    }

    //Renderar: 4Profileworker och 4profileworkgiver
    @GetMapping("/showformforupdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id,
                                    Model model) {

        UserEntity user = userService.getUserById(id);
        model.addAttribute("user", user);

        if (user.isWorker()) {
            model.addAttribute("user", user);
            return "4profileworker";

        } else if (user.isWorkgiver()) {

            model.addAttribute("user", user);
            return "4profileworkgiver";
        }
        return "error";
    }

    //Renderar: 5publicWorker and 5publicworkgiver
    // Show page with public view
    @GetMapping("/showPublicView/{id}")
    public String showPublicView(@PathVariable(value = "id") long id, Model model) {
        UserEntity user = userService.getUserById(id);
        model.addAttribute("user", user);

        if (user.isWorker()) {
            model.addAttribute("user", user);
            return "5publicWorker";

        } else if (user.isWorkgiver()) {

            model.addAttribute("user", user);
            return "5publicworkgiver";
        }
        return "error";
    }

    //Renderar: CreateJob.html
    @GetMapping("/showFormRegisterJob/{id}")
    public String showFormRegisterJob(@ModelAttribute("job") Job job,
                                      @PathVariable(value = "id") long id,
                                      Model model) {

        UserEntity user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "createJob";
    }

    /*************************FUNCTIONS*************************************/
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
            userTwoService.saveUser(user);

        } else if (!password.equals(newpassword)) {
            model.addAttribute("error", "Du har angett olika lösenord, var vänlig försök igen!");
            return "2register";
        }

        return "redirect:/";
    }


    //C - Create Job
    @PostMapping("/saveJob/{id}")
    public String createJob(@ModelAttribute("job") Job job,
                            @ModelAttribute("user") UserEntity userEntity,
                            @PathVariable(value = "id") long id,
                            Model model) {
        jobService.saveJob(job);

        UserEntity user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "redirect:/loginWorkgiver/" + id;
    }

    //Login User
    @PostMapping("login")
    public String login(@ModelAttribute("user") UserEntity userEntity, @RequestParam("username") String username) {

        UserEntity user = userService.getUserByUsername(username);
        Long id = user.getId();

        if (user != null && username.equals(user.getUsername())) {

            if (user.isWorker()) {
                return "redirect:/loginWorker/" + id;

            } else if (user.isWorkgiver()) {
                return "redirect:/loginWorkgiver/" + id;
            }
        }
        return "error";
    }

    //U - Update User
    @PostMapping("/updateUser/{id}")
    public String updateUser(@ModelAttribute("user") UserEntity userEntity,
                             @PathVariable(value = "id") long id,
                             Model model) {
        userService.saveUser(userEntity);
        UserEntity user = userService.getUserById(id);
        model.addAttribute("user", user);
        String message = "Dina uppgifter har ändrats!";
        model.addAttribute("msg", message);

        if (user.isWorker()) {
            model.addAttribute("user", user);
            return "4profileworker";

        } else if (user.isWorkgiver()) {

            model.addAttribute("user", user);
            return "4profileworkgiver";
        }

        return "error";
    }



    //Gammal kod - spara ett tag
    //Renderar: CreateJob.html
    /*
    @GetMapping("/showFormRegisterJob")
    public String registerJob(@ModelAttribute("job") Job job,
                             @ModelAttribute("user") UserEntity userEntity) {
        return "createJob";
    }*/

    //C - Create Job
    /*
    @PostMapping("/saveJob")
    public String saveJob(Job job,
                          @RequestParam("username") long ide) {

        UserEntity user = userService.getUserById(ide);
        Long id = user.getId();
        jobService.saveJob(job);
        return "redirect:/loginWorkgiver/" + id;
    }*/





}
