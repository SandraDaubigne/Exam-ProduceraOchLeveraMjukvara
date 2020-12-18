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

    /*******FÄRDIGA SIDOR*******/
    //Startvy
    @GetMapping("/")
    public String home(){
        return "login";
    }


    //Första vyn där man loggar in
    //2: Skapa funktion för att kunna logga in
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, Model model){

        //Hämtar det valda objektet med hjälp av username
        //Lagrar det i en variabel av samma typ
        //Variabeln avnänds sedan till allt här inne för att hämta värden genom getmetoderna
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

    //När man klickar på skapa konto från loginvy så kommer man hit
    @GetMapping("/register")
    public String registerpage(){
        return "register";
    }

    //När man är klar med sitt konto skickar man detta på submitknappen
    //VÄNTA
    //2: Skapa funktion för att kunna registrera sig.
    //RequestBody för att kunna ta emot JSON
    @RequestMapping(value = "/createuser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createUser(@RequestBody User user){

        entityService.createUser(user);
        //Väntar på kod från Hanna
        return "register";
    }


    /**********INTE FÄRDIGA SIDOR*********/

    //När man trycker på "din profil" så kommer man hit
    //Redigerar
    @GetMapping("privateprofile")
    public String privateProfile(){

        //return "privateworkgiver";
        return "privateworker";
    }

    //När man trycker på "visa profilt läge" inne i din profil så kommer man hit
    @GetMapping("publicprofile")
    public String publicProfile(){

        //return "publicworkgiver";
        return "publicworker";
    }

}
