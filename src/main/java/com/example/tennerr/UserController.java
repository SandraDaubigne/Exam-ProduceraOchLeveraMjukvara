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
            @RequestParam(value = "titel") String titel,
                            @PathVariable(value = "id") long id,
                            Model model) {

        User user = userTwoService.getUserById(id);
        model.addAttribute("user", user);

        job.setUser(user);
        jobService.saveJob(job);

        return "redirect:/loginWorkgiver/" + id;
    }

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

    /************NY DASHBOARD***********/
    @GetMapping("/showDaschboard/{id}")
    public String showDashboard(@PathVariable(value = "id") long id,
                              Model model) {

        //List<Job> jobs= jobService.findAllJobs(id);
        //model.addAttribute("jobs", jobs);

        /*
        if (user.getRolesCategory().isWorker()) {
            model.addAttribute("user", user);
            return "4profileworker";

        } else if (user.getRolesCategory().isWorkgiver()) {

            model.addAttribute("user", user);
            return "4profileworkgiver";
        }*/

        return "daschboard";
    }



    //Login User
    @PostMapping("login")
    public String login(@RequestParam("username") String username) {

        User user = userTwoService.getUserByUsername(username);
        Long id = user.getId();

        if (user != null && username.equals(user.getUsername())) {

            if (user.getRolesCategory().isWorker()) {
                return "redirect:/loginWorker/" + id;

            } else if (user.getRolesCategory().isWorkgiver()) {
                return "redirect:/loginWorkgiver/" + id;
            }
        }
        return "error";
    }



    //Renderar: 4Profileworker och 4profileworkgiver
    @GetMapping("/showformforupdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id,
                                    Model model) {

        User user = userTwoService.getUserById(id);
        model.addAttribute("user", user);

        if (user.getRolesCategory().isWorker()) {
            model.addAttribute("user", user);
            return "4profileworker";

        } else if (user.getRolesCategory().isWorkgiver()) {

            model.addAttribute("user", user);
            return "4profileworkgiver";
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
