package com.example.demo.service;

import com.example.demo.dao.UserRegistrationRepo;
import com.example.demo.entity.UserRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp  implements UserDetailsService{

    @Autowired
    private UserRegistrationRepo userRegistrationRepo;
    


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username:{class user detail service} "+username);

        System.out.println("{getting into userdetails find by email}");
        UserRegistration user = userRegistrationRepo.findByEmail(username);
        System.out.println("{getting result of find by email}");
        // System.out.println(user);        
        if(user == null){
            // LOGGER to print
            System.err.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        // User => is from secuirty 
        System.out.println("No error");
        System.out.println("{class user detail service}"+user.getRoles());
        User userdetails = new User(user.getEmail(), user.getPassword(), user.getRoles());
        System.out.println("{class After making user details}");
        System.out.println(userdetails.getPassword());
        System.out.println(userdetails.getUsername());
        System.out.println(userdetails.getAuthorities());

        return userdetails;
    }
    
}
