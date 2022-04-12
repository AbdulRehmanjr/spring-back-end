package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SecurityServiceImp implements SecurityService {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private DaoAuthenticationProvider authProvider;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean login(String username, String password) {
        System.out.println("{In secuirty service login function}");
        boolean result = false;
        System.out.println("{getting into userdetails load function}");
        UserDetails details = userDetailsService.loadUserByUsername(username);
        // debuging process
        System.out.println("{OUT of userdetails load function}");
        System.out.println("Password: {class secuirty service} "+details.getPassword());
        System.out.println("Username: {class secuirty service} "+details.getUsername());
        System.out.println("Authorities: {class secuirty service} "+details.getAuthorities());
        if(passwordEncoder.matches(password, details.getPassword())) {
            System.out.println("{password match}");
            
            //token 
            System.out.println("{getting into authentication manager}");
            try{
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(details.getUsername(), details.getPassword(), details.getAuthorities());
                
                try {
                    authenticationManager.authenticate(token);
                } catch (BadCredentialsException e) {
                    System.out.println("{bad credentials}");
                }
                System.out.println("{getting out authentication manager}");
                if (token.isAuthenticated() == true) {
                    SecurityContextHolder.getContext().setAuthentication(token);
                    result = true;
                } else {
                    System.out.println("{not authenticated}");
                    throw new RuntimeException("FAILED TO AUTHENTICATE");
                }

            }catch(Exception e){
                System.out.println("{error in authentication manager}");
            }

        
      

        
               
        }
        System.out.println("{secuirty class}"+result);
        
        return result;
    }

}
    

