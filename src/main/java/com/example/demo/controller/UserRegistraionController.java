package com.example.demo.controller;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.dao.UserRegistrationRepo;
import com.example.demo.entity.Login;
import com.example.demo.entity.UserRegistration;
import com.example.demo.service.SecurityService;
import com.example.demo.service.UserRegistrationService;
import com.example.demo.service.User_roleService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserRegistraionController {
    @Autowired
    private UserRegistrationService userRegistrationService;
    @Autowired
    private  User_roleService user_roleService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("/login")
    public String login() {

        return "login";
    }
    
    @GetMapping("/memberpage")
    public String memberpage() {

        // return a url to redirect
        return "memberpage";
    }
    @PostMapping("/login")
    public void login(@RequestBody Login login,HttpServletResponse response) {
        String username = login.getEmail();
        String password = login.getPassword();
        // debuging process
        System.out.println(username);
        System.out.println(password);
        System.out.println("{getting into secuirty service}");
        boolean result = securityService.login(username, password);
        System.out.println("{getting back into secuirty service}");
        if(result == true ) {
            System.out.println("{User controller}");
            // redirect to Url 
            
            // try{
            //         response.sendRedirect("/memberpage");
            // }catch(Exception e){
            //     System.out.println("{error in redirecting}");
            // }

        }
            // try {
            //     response.sendRedirect("/login");
            // } catch (Exception e) {
            //     System.out.println("{error in redirecting}");
            // }
        
    }
    @PostMapping("/signup")
    public String save(@RequestBody UserRegistration userRegistration )   {
       
        System.out.println("Geting data");
        userRegistrationService.save(userRegistration);
        System.out.println(userRegistration.getId());
        user_roleService.save(2,userRegistration.getId().intValue());
        return "registered";
    }
    // @PostMapping(value = "/signup")
    // public ResponseEntity<Void> redirect(@RequestParam Map<String, String> input) {

    //     return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(baseUrl+"/login")).build();
    // }        
    
}
